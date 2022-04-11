package theArcanist.cards;

import basemod.AutoAdd;
import basemod.abstracts.CustomCard;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.evacipated.cardcrawl.mod.stslib.patches.ColoredDamagePatch.DamageActionColorField;
import com.evacipated.cardcrawl.mod.stslib.patches.ColoredDamagePatch.FadeSpeed;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.google.gson.reflect.TypeToken;
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
import theArcanist.icons.Dark;
import theArcanist.icons.Force;
import theArcanist.icons.Ice;
import theArcanist.icons.SoulFire;
import theArcanist.TheArcanist;
import theArcanist.cards.cardvars.CardSaveObject;
import theArcanist.damagemods.*;
import theArcanist.powers.AbstractArcanistPower;
import theArcanist.powers.ResonatingPower;
import theArcanist.util.CardArtRoller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static theArcanist.ArcanistMod.*;
import static theArcanist.cards.AbstractArcanistCard.elenum.*;
import static theArcanist.util.Wiz.*;

@AutoAdd.Ignore
public abstract class AbstractArcanistCard extends CustomCard implements CustomSavable<CardSaveObject> {
    protected CardStrings cardStrings;
    private static final CardStrings thisCardStrings =
            CardCrawlGame.languagePack.getCardStrings(makeID(AbstractArcanistCard.class.getSimpleName()));

    public int secondMagic;
    public int baseSecondMagic;
    public boolean upgradedSecondMagic;
    public boolean isSecondMagicModified;

    private float rotationTimer = 0;
    protected int previewIndex;
    protected ArrayList<AbstractCard> cardToPreview = new ArrayList<>();

    private boolean needsArtRefresh = false;

    public boolean magicOneIsDebuff = false;
    public boolean magicTwoIsDebuff = false;
    public boolean hasScourge = false;

    public boolean beingDiscarded = false;
    public static final String MESSAGE_KEY = "SigilMessage";
    public static final String CAN_NOT_PLAY_MESSAGE = CardCrawlGame.languagePack.getUIString(
            ArcanistMod.makeID(MESSAGE_KEY)).TEXT[0];
    public boolean resonant = false;

    protected int jinx = 0;
    protected int chaos = 0;

    public ArrayList<elenum> damageModList = new ArrayList<>();
    public boolean sigil = false;
    public int extraDraw = 0;
    public int extraEnergy = 0;
    public boolean scourgeIncrease = false;

    private static final Color FLAVOR_BOX_COLOR = Color.PURPLE.cpy();
    private static final Color FLAVOR_TEXT_COLOR = new Color(1.0F, 0.9725F, 0.8745F, 1.0F);

    public static final String COLD_STRING = Ice.CODE;
    public static final String FORCE_STRING = Force.CODE;
    public static final String SOULFIRE_STRING = SoulFire.CODE;
    public static final String DARK_STRING = Dark.CODE;

    public AbstractArcanistCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        this(cardID, cost, type, rarity, target, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
    }

    public AbstractArcanistCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(cardID, "", getCardTextureString(cardID.replace(modID + ":", ""), type),
                cost, "", type, color, rarity, target);

        if (cardStrings == null)
            cardStrings = CardCrawlGame.languagePack.getCardStrings(this.cardID);
        name = cardStrings.NAME;

        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);

        if (textureImg.contains("ui/missing.png")) {
            if (CardLibrary.getAllCards() != null && !CardLibrary.getAllCards().isEmpty()) {
                CardArtRoller.computeCard(this);
            } else
                needsArtRefresh = true;
        }

        applyAttributes();
        initializeDescription();
    }

    protected abstract void applyAttributes();

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

    protected int getJinxAmountCard(AbstractMonster m) {
        int x = getJinxAmount(m);
        if (scourgeIncrease)
            x *= 2;
        return x;
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
        boolean cold = false;
        boolean dark = false;
        boolean force = false;
        boolean fire = false;
        for (elenum x : damageModList) {
            if (x == ICE)
                cold = true;
            else if (x == DARK)
                dark = true;
            else if (x == FORCE)
                force = true;
            else if (x == FIRE)
                fire = true;
        }
        int jinxAmount = jinx;
        if (scourgeIncrease)
            jinxAmount *= 2;
        applyToSelf(new ResonatingPower(baseDamage, cold, dark, force, fire, jinxAmount, chaos, extraDraw, extraEnergy));
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

    @Override
    public void initializeDescription() {
        if (cardStrings == null)
            cardStrings = CardCrawlGame.languagePack.getCardStrings(this.cardID);
        if (upgraded && cardStrings.UPGRADE_DESCRIPTION != null)
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        else
            rawDescription = cardStrings.DESCRIPTION;

        if (damageModList != null) {
            if (damageModList.contains(ICE))
                rawDescription = rawDescription.replace("!D! ", "!D! " + COLD_STRING + " ");
            if (damageModList.contains(FIRE))
                rawDescription = rawDescription.replace("!D! ", "!D! " + SOULFIRE_STRING + " ");
            if (damageModList.contains(DARK))
                rawDescription = rawDescription.replace("!D! ", "!D! " + DARK_STRING + " ");
            if (damageModList.contains(FORCE))
                rawDescription = rawDescription.replace("!D! ", "!D! " + FORCE_STRING + " ");
        }

        if (hasScourge && !scourgeIncrease)
            rawDescription = rawDescription.replace("!ScourgeString!", "[arcanistmod:ScourgeIcon]");
        else if (hasScourge)
            rawDescription = rawDescription.replace("!ScourgeString!", "2 [arcanistmod:ScourgeIcon]");

        if (selfRetain)
            rawDescription = thisCardStrings.EXTENDED_DESCRIPTION[0] + rawDescription;
        if (sigil)
            rawDescription = thisCardStrings.EXTENDED_DESCRIPTION[1] + rawDescription;

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

    public void addModifier(elenum element, boolean tips) {
        if (damageModList.contains(element))
            return;
        damageModList.add(element);
        if (element == ICE)
            DamageModifierManager.addModifier(this, new IceDamage(tips));
        if (element == elenum.FIRE)
            DamageModifierManager.addModifier(this, new SoulFireDamage(tips));
        if (element == elenum.FORCE)
            DamageModifierManager.addModifier(this, new ForceDamage(tips));
        if (element == elenum.DARK)
            DamageModifierManager.addModifier(this, new DarkDamage(tips));
        initializeDescription();
    }

    public void addModifier(elenum element) {
        addModifier(element, true);
    }

    // These shortcuts are specifically for cards. All other shortcuts that aren't specifically for cards can go in Wiz.
    public void dmg(AbstractCreature m, AbstractGameAction.AttackEffect fx) {
        if (damageModList.size() == 1) {
            elenum ele = damageModList.get(0);
            Color color = Color.WHITE;
            if (ele == ICE)
                color = Color.BLUE.cpy();
            else if (ele == FORCE)
                color = Color.PINK.cpy();
            else if (ele == FIRE)
                color = Color.PURPLE.cpy();
            else if (ele == DARK)
                color = Color.BLACK.cpy();
            else
                atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL), fx));
            dmg(m, fx, color);
        }
        else if (damageModList.size() > 1) {
            DamageAction damageAction = new DamageAction(m,
                    new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL), fx);
            DamageActionColorField.rainbow.set(damageAction, true);
            atb(damageAction);
        }
        else
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
        card.sigil = sigil;
        card.selfRetain = selfRetain;
        card.magicOneIsDebuff = magicOneIsDebuff;
        card.magicTwoIsDebuff = magicTwoIsDebuff;
        card.resonant = resonant;
        card.extraDraw = extraDraw;
        card.extraEnergy = extraEnergy;
        card.scourgeIncrease = scourgeIncrease;
        for (elenum ele : damageModList)
            card.addModifier(ele);
        card.initializeDescription();
        return card;
    }

    @Override
    public CardSaveObject onSave() {
        CardSaveObject obj = new CardSaveObject();
        obj.elements = damageModList;
        obj.sigil = sigil;
        obj.retain = selfRetain;
        obj.scourgeIncrease = scourgeIncrease;
        obj.extraDraw = extraDraw;
        obj.extraEnergy = extraEnergy;
        obj.baseDamage = baseDamage;
        obj.baseSecondMagic = baseSecondMagic;
        obj.baseMagic = baseMagicNumber;
        return obj;
    }

    @Override
    public void onLoad(CardSaveObject obj) {
        for (elenum ele : obj.elements)
            addModifier(ele);
        sigil = obj.sigil;
        if (sigil)
            cost = -2;
        selfRetain = obj.retain;
        scourgeIncrease = obj.scourgeIncrease;
        extraDraw = obj.extraDraw;
        extraEnergy = obj.extraEnergy;
        baseDamage = obj.baseDamage;
        baseSecondMagic = obj.baseSecondMagic;
        baseMagicNumber = obj.baseMagic;
    }

    @Override
    public Type savedType() {
        return new TypeToken<CardSaveObject>(){}.getType();
    }

    public enum elenum {
        ICE,
        FIRE,
        FORCE,
        DARK
    }
}
