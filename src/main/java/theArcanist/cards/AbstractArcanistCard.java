package theArcanist.cards;

import basemod.AutoAdd;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.evacipated.cardcrawl.mod.stslib.patches.ColoredDamagePatch.DamageActionColorField;
import com.evacipated.cardcrawl.mod.stslib.patches.ColoredDamagePatch.FadeSpeed;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.ArcanistMod;
import theArcanist.TheArcanist;
import theArcanist.cards.damageMods.DarkDamage;
import theArcanist.cards.damageMods.ForceDamage;
import theArcanist.cards.damageMods.IceDamage;
import theArcanist.cards.damageMods.SoulFireDamage;
import theArcanist.powers.AbstractArcanistPower;
import theArcanist.powers.ResonatingPower;
import theArcanist.util.CardArtRoller;

import java.util.ArrayList;
import java.util.List;

import static theArcanist.ArcanistMod.makeImagePath;
import static theArcanist.ArcanistMod.modID;
import static theArcanist.util.Wiz.*;

@AutoAdd.Ignore
public abstract class AbstractArcanistCard extends CustomCard {
    protected CardStrings cardStrings;

    public int secondMagic;
    public int baseSecondMagic;
    public boolean upgradedSecondMagic;
    public boolean isSecondMagicModified;

    private float rotationTimer = 0;
    protected int previewIndex;
    protected ArrayList<AbstractCard> cardToPreview = new ArrayList<>();

    private boolean needsArtRefresh = false;
    protected boolean magicOneIsDebuff = false;
    protected boolean magicTwoIsDebuff = false;

    public boolean beingDiscarded = false;
    public static final String MESSAGE_KEY = "SigilMessage";
    public static final String CAN_NOT_PLAY_MESSAGE = CardCrawlGame.languagePack.getUIString(
            ArcanistMod.makeID(MESSAGE_KEY)).TEXT[0];
    public boolean sigil = false;
    public boolean resonant = false;

    protected int cardDraw = 0;
    protected int energy = 0;
    protected int jinx = 0;
    protected int chaos = 0;

    private static final Color FLAVOR_BOX_COLOR = Color.PURPLE.cpy();
    private static final Color FLAVOR_TEXT_COLOR = new Color(1.0F, 0.9725F, 0.8745F, 1.0F);

    public AbstractArcanistCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        this(cardID, cost, type, rarity, target, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
    }

    public AbstractArcanistCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(cardID, "", getCardTextureString(cardID.replace(modID + ":", ""), type),
                cost, "", type, color, rarity, target);

        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);

        if (textureImg.contains("ui/missing.png")) {
            if (CardLibrary.getAllCards() != null && !CardLibrary.getAllCards().isEmpty()) {
                CardArtRoller.computeCard(this);
            } else
                needsArtRefresh = true;
        }
    }

    public boolean checkMagicOneIsDebuff() {return magicOneIsDebuff;}

    public boolean checkMagicTwoIsDebuff() {return magicTwoIsDebuff;}

    @Override
    public List<String> getCardDescriptors() {
        ArrayList<String> retVal = new ArrayList<>();
        if (sigil)
            retVal.add("Sigil");
        return retVal;
    }

    @Override
    public void triggerOnManualDiscard() {
        if (!sigil)
            return;
        beingDiscarded = true;
        autoPlayWhenDiscarded();
        // Powers that trigger on discard
        forAllMonstersLiving(m -> {
            for (AbstractPower pow : m.powers)
                if (pow instanceof AbstractArcanistPower)
                    ((AbstractArcanistPower) pow).onDiscardSigil();
        });
        for (AbstractPower pow : adp().powers) {
            if (pow instanceof AbstractArcanistPower)
                ((AbstractArcanistPower) pow).onDiscardSigil();
        }
    }

    protected void autoPlayWhenDiscarded() {
        AbstractDungeon.player.discardPile.removeCard(this);
        AbstractDungeon.getCurrRoom().souls.remove(this);
        AbstractDungeon.player.limbo.group.add(this);
        target_y = Settings.HEIGHT / 2.0f;
        target_x = Settings.WIDTH / 2.0f;
        targetAngle = 0;
        targetDrawScale = 0.8f;
        lighten(true);
        att(new NewQueueCardAction(this, true, false, true));
        att(new UnlimboAction(this));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean superBool = super.canUse(p, m);

        if (!superBool) {
            beingDiscarded = false;
            return false;
        }
        else if (!beingDiscarded && sigil) {
            cantUseMessage = CAN_NOT_PLAY_MESSAGE;
            return false;
        }

        return true;
    }

    public final void use(AbstractPlayer p, AbstractMonster m) {
        if (sigil)
            beingDiscarded = false;
        onUse(p, m);
        if (resonant)
            resonate();
    }

    protected abstract void onUse(AbstractPlayer p, AbstractMonster m);

    private void resonate() {
        List<AbstractDamageModifier> eleList = DamageModifierManager.modifiers(this);
        boolean cold = false;
        boolean dark = false;
        boolean force = false;
        boolean fire = false;
        for (AbstractDamageModifier x : eleList) {
            if (x instanceof IceDamage)
                cold = true;
            else if (x instanceof DarkDamage)
                dark = true;
            else if (x instanceof ForceDamage)
                force = true;
            else if (x instanceof SoulFireDamage)
                fire = true;
        }
        applyToSelf(new ResonatingPower(baseDamage, cold, dark, force, fire, jinx, chaos, cardDraw, energy));
    }

    @Override
    protected Texture getPortraitImage() {
        if (textureImg.contains("ui/missing.png")) {
            return CardArtRoller.getPortraitTexture(this);
        }
        else
            return super.getPortraitImage();
    }

    public static String getCardTextureString(final String cardName, final AbstractCard.CardType cardType) {
        String textureString;

        switch (cardType) {
            case ATTACK:
            case POWER:
            case SKILL:
                textureString = makeImagePath("cards/" + cardName + ".png");
                break;
            default:
                textureString = makeImagePath("ui/missing.png");
                break;
        }

        FileHandle h = Gdx.files.internal(textureString);
        if (!h.exists()) {
            textureString = makeImagePath("ui/missing.png");
        }
        return textureString;
    }

    public void resetAttributes() {
        super.resetAttributes();
        secondMagic = baseSecondMagic;
        isSecondMagicModified = false;
    }

    public void displayUpgrades() {
        super.displayUpgrades();
        if (upgradedSecondMagic) {
            secondMagic = baseSecondMagic;
            isSecondMagicModified = true;
        }
    }

    protected void upgradeSecondMagic(int upgradeAmount) {
        baseSecondMagic += upgradeAmount;
        secondMagic = baseSecondMagic;
        upgradedSecondMagic = true;
    }

    protected void upgradeCardToPreview() {
        for (AbstractCard q : cardToPreview) {
            q.upgrade();
        }
    }

    public abstract void upp();

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upp();
            if (cardStrings.UPGRADE_DESCRIPTION != null)
                rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void additionalParsing() {};

    @Override
    public void initializeDescription() {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(this.cardID);
        rawDescription = cardStrings.DESCRIPTION;
        name = originalName = cardStrings.NAME;
        additionalParsing();
        super.initializeDescription();
    }

    public void update() {
        super.update();
        if (needsArtRefresh) {
            CardArtRoller.computeCard(this);
        }
        if (!cardToPreview.isEmpty()) {
            if (hb.hovered) {
                if (rotationTimer <= 0F) {
                    rotationTimer = getRotationTimeNeeded();
                    if (previewIndex >= cardToPreview.size())
                        previewIndex = 0;
                    cardsToPreview = cardToPreview.get(previewIndex);
                    if (previewIndex == cardToPreview.size() - 1) {
                        previewIndex = 0;
                    } else {
                        previewIndex++;
                    }
                } else {
                    rotationTimer -= Gdx.graphics.getDeltaTime();
                }
            }
        }
    }

    protected float getRotationTimeNeeded() {
        return 1f;
    }

    // These shortcuts are specifically for cards. All other shortcuts that aren't specifically for cards can go in Wiz.
    public void dmg(AbstractCreature m, AbstractGameAction.AttackEffect fx) {
        atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL), fx));
    }

    public void dmg(AbstractCreature m, AbstractGameAction.AttackEffect fx, Color color) {
        DamageAction damageAction = new DamageAction(m,
                new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL), fx);
        DamageActionColorField.damageColor.set(damageAction, color);
        DamageActionColorField.fadeSpeed.set(damageAction, FadeSpeed.SLOWISH);
        atb(damageAction);
    }

    public void dmgTop(AbstractCreature m, AbstractGameAction.AttackEffect fx) {
        att(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL), fx));
    }

    public void allDmg(AbstractGameAction.AttackEffect fx) {
        atb(new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage, DamageInfo.DamageType.NORMAL, fx));
    }

    public void allDmg(AbstractGameAction.AttackEffect fx, Color color) {
        DamageAllEnemiesAction action = new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage,
                DamageInfo.DamageType.NORMAL, fx);
        DamageActionColorField.damageColor.set(action, color);
        DamageActionColorField.fadeSpeed.set(action, FadeSpeed.SLOWISH);
        atb(action);
    }

    protected void blck() {
        atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
    }

    protected void blckTop() {
        att(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
    }

    protected void upMagic(int x) {upgradeMagicNumber(x);}

    protected void upMagic2(int x) {upgradeSecondMagic(x);}

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractArcanistCard card = (AbstractArcanistCard) super.makeStatEquivalentCopy();
        card.secondMagic = secondMagic;
        card.baseSecondMagic = baseSecondMagic;
        card.upgradedSecondMagic = upgradedSecondMagic;
        card.isSecondMagicModified = isSecondMagicModified;
        card.sigil = sigil;
        card.magicOneIsDebuff = magicOneIsDebuff;
        card.magicTwoIsDebuff = magicTwoIsDebuff;
        card.resonant = resonant;
        card.cardDraw = cardDraw;
        card.energy = energy;
        return card;
    }
}
