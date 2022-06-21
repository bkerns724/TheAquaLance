package theArcanist.cards;

import basemod.AutoAdd;
import basemod.abstracts.CustomCard;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.evacipated.cardcrawl.mod.stslib.patches.BindingPatches;
import com.evacipated.cardcrawl.mod.stslib.patches.ColoredDamagePatch.DamageActionColorField;
import com.evacipated.cardcrawl.mod.stslib.patches.ColoredDamagePatch.FadeSpeed;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
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
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import theArcanist.TheArcanist;
import theArcanist.cards.cardvars.CardSaveObject;
import theArcanist.damagemods.DarkDamage;
import theArcanist.damagemods.ForceDamage;
import theArcanist.damagemods.IceDamage;
import theArcanist.damagemods.SoulFireDamage;
import theArcanist.icons.Dark;
import theArcanist.icons.Force;
import theArcanist.icons.Ice;
import theArcanist.icons.SoulFire;
import theArcanist.powers.AbstractArcanistPower;
import theArcanist.powers.BoostedSigilPower;
import theArcanist.relics.ManaPurifier;
import theArcanist.util.CardArtRoller;
import theArcanist.vfx.DarkWaveEffect;

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
    protected boolean overrideRawDesc = false;

    public boolean magicOneIsDebuff = false;
    public boolean magicTwoIsDebuff = false;
    public boolean hasScourge = false;

    public boolean beingDiscarded = false;

    public ArrayList<elenum> damageModList = new ArrayList<>();
    public boolean sigil = false;
    public boolean debuffIncrease = false;
    public boolean scourgeIncrease = false;

    private static final Color FLAVOR_BOX_COLOR = new Color(0.45f, 0, 0.65f, 1.0f);
    private static final Color FLAVOR_TEXT_COLOR = new Color(1.0F, 0.9725F, 0.8745F, 1.0F);

    public static final String COLD_STRING = Ice.CODE;
    public static final String FORCE_STRING = Force.CODE;
    public static final String SOULFIRE_STRING = SoulFire.CODE;
    public static final String DARK_STRING = Dark.CODE;

    public enum elenum {
        ICE,
        FIRE,
        FORCE,
        DARK
    }

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
        if (adp() != null && adp().hasRelic(ManaPurifier.ID)) {
            DamageModifierManager.clearModifiers(this);
            damageModList.clear();
        }
        initializeDescription();
    }

    protected abstract void applyAttributes();

    @Override
    public List<String> getCardDescriptors() {
        ArrayList<String> retVal = new ArrayList<>();
        if (sigil)
            retVal.add(thisCardStrings.EXTENDED_DESCRIPTION[8]);
        return retVal;
    }

    public void onPickup() {}

    @Override
    public void triggerOnManualDiscard() {
        if (!sigil)
            return;
        beingDiscarded = true;
        autoPlayWhenDiscarded();
        BindingPatches.DisableReactionaryActionBinding.disableBefore(null);
        forAllMonstersLiving(m -> {
            for (AbstractPower pow : m.powers)
                if (pow instanceof AbstractArcanistPower)
                    ((AbstractArcanistPower) pow).onDiscardSigil();
        });
        for (AbstractPower pow : adp().powers) {
            if (pow instanceof AbstractArcanistPower)
                ((AbstractArcanistPower) pow).onDiscardSigil();
        }
        BindingPatches.DisableReactionaryActionBinding.enableAfter(null);
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

        AbstractPower pow = adp().getPower(BoostedSigilPower.POWER_ID);
        if (purgeOnUse || pow == null || pow.amount <= 0) {
            att(new NewQueueCardAction(this, true, false, true));
            att(new UnlimboAction(this));
            return;
        }

        pow.flash();
        AbstractCard tmp = makeSameInstanceOf();
        adp().limbo.addToBottom(tmp);
        tmp.current_x = current_x;
        tmp.current_y = current_y;
        tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
        tmp.target_y = (float) Settings.HEIGHT / 2.0F;

        tmp.purgeOnUse = true;
        pow.amount--;
        if (pow.amount == 0)
            atb(new RemoveSpecificPowerAction(adp(), adp(), pow));

        att(new NewQueueCardAction(tmp, true, false, true));
        att(new UnlimboAction(tmp));

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
            cantUseMessage = thisCardStrings.EXTENDED_DESCRIPTION[9];
            return false;
        }

        return true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (sigil)
            beingDiscarded = false;
        onUse(p, m);
    }

    protected abstract void onUse(AbstractPlayer p, AbstractMonster m);

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
        for (AbstractCard q : cardToPreview)
            q.upgrade();
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

    protected String getCustomString() {
        return "";
    }

    @Override
    public void initializeDescription() {
        if (!overrideRawDesc) {
            if (cardStrings == null)
                cardStrings = CardCrawlGame.languagePack.getCardStrings(this.cardID);
            if (upgraded && cardStrings.UPGRADE_DESCRIPTION != null)
                rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            else
                rawDescription = cardStrings.DESCRIPTION;
        }

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

        rawDescription = rawDescription.replace("!C!", getCustomString());

        if (hasScourge && !scourgeIncrease)
            rawDescription = rawDescription.replace("!ScourgeString!", "[arcanistmod:ScourgeIcon]");
        else if (hasScourge)
            rawDescription = rawDescription.replace("!ScourgeString!", "2 [arcanistmod:ScourgeIcon]");

        if (selfRetain)
            rawDescription = thisCardStrings.EXTENDED_DESCRIPTION[0] + rawDescription;
        if (sigil)
            rawDescription = thisCardStrings.EXTENDED_DESCRIPTION[1] + rawDescription;

        if (this instanceof AbstractResonantCard)
            rawDescription = rawDescription + thisCardStrings.EXTENDED_DESCRIPTION[6];

        if (exhaust)
            rawDescription = rawDescription + thisCardStrings.EXTENDED_DESCRIPTION[7];

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
        atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL), fx));
    }

    protected AbstractGameAction.AttackEffect getAttackEffect() {
        /*
        int amount = damage;
        if (isMultiDamage && multiDamage.length > 0) {
            amount = multiDamage[0];
            for (int x : multiDamage)
                if (x < amount)
                    amount = x;
        }*/
        if (damageModList.size() == 1) {
            elenum ele = damageModList.get(0);
            if (ele == ICE)
                return Enums.ICE;
            else if (ele == FORCE)
                return Enums.FIST;
            else if (ele == FIRE)
                return Enums.SOUL_FIRE;
            else if (ele == DARK)
                return Enums.DARK_COIL;
            else
                return AbstractGameAction.AttackEffect.SLASH_DIAGONAL;
        } else if (damageModList.size() > 1)
            return Enums.DARK_WAVE;
        return getDefaultAttackEffect();
    }

    protected AbstractGameAction.AttackEffect getDefaultAttackEffect() {
        return AbstractGameAction.AttackEffect.NONE;
    }

    protected Color getAttackColor() {
        if (damageModList.size() == 1) {
            elenum ele = damageModList.get(0);
            if (ele == ICE)
                return Color.BLUE.cpy();
            else if (ele == FORCE)
                return Color.PINK.cpy();
            else if (ele == FIRE)
                return Color.PURPLE.cpy();
            else if (ele == DARK)
                return Color.BLACK.cpy();
            return null;
        }
        else if (damageModList.size() > 1)
            return new Color(1, 1, 1, 0);
        return getDefaultColor();
    }

    protected Color getDefaultColor() {return null;}

    public void dmg(AbstractMonster m) {
        dmg(m, getAttackEffect(), getAttackColor());
    }

    public void dmg(AbstractCreature m, AbstractGameAction.AttackEffect fx, Color color) {
        if (color != null && color.a == 0) {
            dmg(m, fx, true);
            return;
        }

        DamageAction damageAction = new DamageAction(m,
                new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL), fx);
        if (fx == Enums.DARK_WAVE)
            vfx(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, m.hb.cX), 0.5F);
        if (fx == AbstractGameAction.AttackEffect.LIGHTNING) {
            atb(new SFXAction("ORB_LIGHTNING_EVOKE"));
            atb(new VFXAction(new LightningEffect(m.drawX, m.drawY), 0));
        }
        if (color == null) {
            atb(damageAction);
            return;
        }
        DamageActionColorField.damageColor.set(damageAction, color);
        DamageActionColorField.fadeSpeed.set(damageAction, FadeSpeed.SLOWISH);
        atb(damageAction);
    }

    public void dmg(AbstractCreature m, AbstractGameAction.AttackEffect fx, boolean rainbow) {
        DamageAction damageAction = new DamageAction(m,
                new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL), fx);
        DamageActionColorField.rainbow.set(damageAction, rainbow);
        if (fx == Enums.DARK_WAVE)
            vfx(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, m.hb.cX), 0.5F);
        if (fx == AbstractGameAction.AttackEffect.LIGHTNING) {
            atb(new SFXAction("ORB_LIGHTNING_EVOKE"));
            atb(new VFXAction(new LightningEffect(m.drawX, m.drawY), 0));
        }
        atb(damageAction);
    }

    public void dmgTop(AbstractCreature m, AbstractGameAction.AttackEffect fx) {
        att(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL), fx));
    }

    public void allDmg() {
        allDmg(getAttackEffect(), getAttackColor());
    }

    public void allDmg(AbstractGameAction.AttackEffect fx) {
        if (fx == Enums.DARK_WAVE)
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
                if (!m.isDeadOrEscaped() && !m.halfDead)
                    vfx(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, m.hb.cX), 0.5F);

        if (fx == AbstractGameAction.AttackEffect.LIGHTNING)
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
                if (!m.isDeadOrEscaped() && !m.halfDead)
                    vfx(new LightningEffect(m.drawX, m.drawY), 0);

        atb(new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage, DamageInfo.DamageType.NORMAL, fx));
    }

    public void allDmg(AbstractGameAction.AttackEffect fx, Color color) {
        if (color != null && color.a == 0) {
            allDmg(fx, true);
            return;
        }
        DamageAllEnemiesAction action = new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage,
                DamageInfo.DamageType.NORMAL, fx);

        if (fx == Enums.DARK_WAVE)
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
                if (!m.isDeadOrEscaped() && !m.halfDead)
                    vfx(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, m.hb.cX), 0.5F);

        if (fx == AbstractGameAction.AttackEffect.LIGHTNING)
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
                if (!m.isDeadOrEscaped() && !m.halfDead)
                    vfx(new LightningEffect(m.drawX, m.drawY), 0);

        if (color == null) {
            atb(action);
            return;
        }
        DamageActionColorField.damageColor.set(action, color);
        DamageActionColorField.fadeSpeed.set(action, FadeSpeed.SLOWISH);
        atb(action);
    }

    public void allDmg(AbstractGameAction.AttackEffect fx, boolean rainbow) {
        if (fx == Enums.DARK_WAVE)
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
                if (!m.isDeadOrEscaped() && !m.halfDead)
                    vfx(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, m.hb.cX), 0.5F);

        if (fx == AbstractGameAction.AttackEffect.LIGHTNING)
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
                if (!m.isDeadOrEscaped() && !m.halfDead)
                    vfx(new LightningEffect(m.drawX, m.drawY), 0);

        DamageAllEnemiesAction action = new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage,
                DamageInfo.DamageType.NORMAL, fx);
        DamageActionColorField.rainbow.set(action, rainbow);
        atb(action);
    }

    public void blck() {
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
        card.scourgeIncrease = scourgeIncrease;
        card.debuffIncrease = debuffIncrease;
        card.damageModList.clear();
        DamageModifierManager.clearModifiers(card);
        if (adp() == null || !adp().hasRelic(ManaPurifier.ID)) {
            for (elenum ele : damageModList)
                card.addModifier(ele);
        }
        card.initializeDescription();
        return card;
    }

    @Override
    public CardSaveObject onSave() {
        CardSaveObject obj = new CardSaveObject();
        obj.elements = damageModList;
        obj.sigil = sigil;
        obj.retain = selfRetain;
        obj.debuffIncrease = debuffIncrease;
        obj.scourgeIncrease = scourgeIncrease;
        obj.baseDamage = baseDamage;
        obj.baseBlock = baseBlock;
        obj.baseMagic = baseMagicNumber;
        obj.baseSecondMagic = baseSecondMagic;
        return obj;
    }

    @Override
    public void onLoad(CardSaveObject obj) {
        damageModList.clear();
        DamageModifierManager.clearModifiers(this);
        if (adp() == null || adp().hasRelic(ManaPurifier.ID))
            for (elenum ele : obj.elements)
                addModifier(ele);
        sigil = obj.sigil;
        if (sigil)
            cost = -2;
        selfRetain = obj.retain;
        debuffIncrease = obj.debuffIncrease;
        scourgeIncrease = obj.scourgeIncrease;
        baseDamage = obj.baseDamage;
        baseBlock = obj.baseBlock;
        baseMagicNumber = obj.baseMagic;
        baseSecondMagic = obj.baseSecondMagic;
    }

    @Override
    public Type savedType() {
        return new TypeToken<CardSaveObject>(){}.getType();
    }
}
