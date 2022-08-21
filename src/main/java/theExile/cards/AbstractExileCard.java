package theExile.cards;

import basemod.AutoAdd;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.evacipated.cardcrawl.mod.stslib.patches.BindingPatches;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;
import com.megacrit.cardcrawl.monsters.ending.SpireSpear;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.SurroundedPower;
import theExile.TheExile;
import theExile.actions.AttackAction;
import theExile.cards.cardUtil.Resonance;
import theExile.cards.cardvars.CardSaveObject;
import theExile.damagemods.*;
import theExile.icons.*;
import theExile.powers.AbstractExilePower;
import theExile.powers.ConvertPower;
import theExile.relics.ManaPurifier;
import theExile.util.CardArtRoller;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static theExile.ExileMod.*;
import static theExile.cards.AbstractExileCard.elenum.*;
import static theExile.util.Wiz.*;

@AutoAdd.Ignore
public abstract class AbstractExileCard extends CustomCard implements CustomSavable<CardSaveObject> {
    protected CardStrings cardStrings;
    private static final CardStrings thisCardStrings =
            CardCrawlGame.languagePack.getCardStrings(makeID(AbstractExileCard.class.getSimpleName()));

    public int secondMagic;
    public int baseSecondMagic;
    public boolean upgradedSecondMagic;
    public boolean isSecondMagicModified;

    public int thirdMagic;
    public int baseThirdMagic;
    public boolean upgradedThirdMagic;
    public boolean isThirdMagicModified;

    private float rotationTimer = 0;
    protected int previewIndex;
    protected ArrayList<AbstractCard> cardToPreview = new ArrayList<>();

    protected boolean needsArtRefresh = false;
    protected boolean overrideRawDesc = false;

    public boolean magicOneIsDebuff = false;
    public boolean magicTwoIsDebuff = false;

    public boolean beingDiscarded = false;

    public ArrayList<elenum> damageModList = new ArrayList<>();
    public boolean sigil = false;
    public boolean debuffIncrease = false;

    private static final Color FLAVOR_BOX_COLOR = new Color(0.45f, 0, 0.65f, 1.0f);
    private static final Color FLAVOR_TEXT_COLOR = new Color(1.0F, 0.9725F, 0.8745F, 1.0F);

    public static final String COLD_STRING = Ice.CODE;
    public static final String FORCE_STRING = Force.CODE;
    public static final String SOULFIRE_STRING = DemonFire.CODE;
    public static final String DARK_STRING = Eldritch.CODE;
    public static final String LIGHTNING_STRING = Lightning.CODE;

    public static final int DAMAGE_THRESHOLD_M = 15;
    public static final int DAMAGE_THRESHOLD_L = 50;

    public boolean resPoof = false;

    public enum elenum {
        ICE,
        FIRE,
        FORCE,
        DARK,
        LIGHTNING
    }

    public AbstractExileCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        this(cardID, cost, type, rarity, target, TheExile.Enums.EXILE_BROWN_COLOR);
    }

    public AbstractExileCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
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
    public void applyPowers() {
        super.applyPowers();
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
                if (pow instanceof AbstractExilePower)
                    ((AbstractExilePower) pow).onDiscardSigil();
        });
        for (AbstractPower pow : adp().powers) {
            if (pow instanceof AbstractExilePower)
                ((AbstractExilePower) pow).onDiscardSigil();
        }
        BindingPatches.DisableReactionaryActionBinding.enableAfter(null);
    }

    protected void autoPlayWhenDiscarded() {
        AbstractDungeon.player.discardPile.removeCard(this);
        AbstractDungeon.player.drawPile.removeCard(this);
        AbstractDungeon.player.hand.removeCard(this);
        AbstractDungeon.getCurrRoom().souls.remove(this);
        AbstractDungeon.player.limbo.group.add(this);
        target_y = Settings.HEIGHT / 2.0f;
        target_x = Settings.WIDTH / 2.0f;
        targetAngle = 0;
        targetDrawScale = 0.8f;
        lighten(true);

        if (this.target == CardTarget.ENEMY) {
            AbstractMonster monster = AbstractDungeon.getMonsters().getRandomMonster(null,true,
                    AbstractDungeon.cardRandomRng);
            att(new NewQueueCardAction(this, monster, false, true));
        }else
            att(new NewQueueCardAction(this, true, false, true));
        att(new UnlimboAction(this));
    }

    protected int getJinxAmountCard(AbstractMonster m) {
        return getJinxAmount(m);
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean superBool = super.canUse(p, m);

        if (!superBool)
            return false;
        else if (!beingDiscarded && sigil) {
            cantUseMessage = thisCardStrings.EXTENDED_DESCRIPTION[9];
            return false;
        }

        return true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        onUse(p, m);
        if (sigil)
            beingDiscarded = false;
        boolean convert = (!exhaust && !purgeOnUse && (type == AbstractCard.CardType.ATTACK || type == AbstractCard.CardType.SKILL)
                && adp() != null && adp().hasPower(ConvertPower.POWER_ID) && !(this instanceof AbstractResonantCard));
        if (convert) {
            Resonance resonance = new Resonance();
            resonance.cards.add((AbstractExileCard) makeStatEquivalentCopy());
            resonance.toPower();
            AbstractPower pow = adp().getPower(ConvertPower.POWER_ID);
            atb(new ReducePowerAction(adp(), adp(), pow, 1));
            resPoof = true;
        } else if (this instanceof AbstractResonantCard)
            ((AbstractResonantCard) this).resonance.toPower();
    }

    public abstract void onUse(AbstractPlayer p, AbstractMonster m);

    public void onTarget(AbstractMonster m) {}

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
        thirdMagic = baseThirdMagic;
        isThirdMagicModified = false;
    }

    public void displayUpgrades() {
        super.displayUpgrades();
        if (upgradedSecondMagic) {
            secondMagic = baseSecondMagic;
            isSecondMagicModified = true;
        }
        if (upgradedThirdMagic) {
            thirdMagic = baseThirdMagic;
            isThirdMagicModified = true;
        }
    }

    protected void upgradeSecondMagic(int upgradeAmount) {
        baseSecondMagic += upgradeAmount;
        secondMagic = baseSecondMagic;
        upgradedSecondMagic = true;
    }

    protected void upgradeThirdMagic(int upgradeAmount) {
        baseThirdMagic += upgradeAmount;
        thirdMagic = baseThirdMagic;
        upgradedThirdMagic = true;
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
            if (damageModList.contains(LIGHTNING))
                rawDescription = rawDescription.replace("!D! ", "!D! " + LIGHTNING_STRING + " ");
        }

        rawDescription = rawDescription.replace("!C!", getCustomString());

        if (selfRetain)
            rawDescription = thisCardStrings.EXTENDED_DESCRIPTION[0] + rawDescription;
        if (isInnate)
            rawDescription = thisCardStrings.EXTENDED_DESCRIPTION[11] + rawDescription;
        if (isEthereal)
            rawDescription = thisCardStrings.EXTENDED_DESCRIPTION[10] + rawDescription;
        if (sigil)
            rawDescription = thisCardStrings.EXTENDED_DESCRIPTION[1] + rawDescription;

        if (this instanceof AbstractResonantCard && ((AbstractResonantCard) this).resonance != null) {
            rawDescription = rawDescription + thisCardStrings.EXTENDED_DESCRIPTION[6];
            int amount = ((AbstractResonantCard) this).resonance.amount;
            rawDescription = rawDescription.replace("!Res!", Integer.toString(amount));
        }

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

    public String cardArtCopy() {
        return null;
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
            DamageModifierManager.addModifier(this, new DemonFireDamage(tips));
        if (element == elenum.FORCE)
            DamageModifierManager.addModifier(this, new ForceDamage(tips));
        if (element == elenum.DARK)
            DamageModifierManager.addModifier(this, new EldritchDamage(tips));
        if (element == LIGHTNING)
            DamageModifierManager.addModifier(this, new DeathLightningDamage(tips));

        if (this instanceof AbstractResonantCard)
            if (!((AbstractResonantCard) this).resonance.damageMods.contains(element))
                ((AbstractResonantCard) this).resonance.damageMods.add(element);
        initializeDescription();
    }

    public void addModifier(ArrayList<elenum> elements, boolean tips) {
        for (elenum e : elements)
            addModifier(e, tips);
    }

    public void addModifier(elenum element) {
        addModifier(element, true);
    }

    protected AbstractGameAction.AttackEffect getAttackEffect() {
        int amount = getDamageForVFX();
        if (this instanceof AbstractResonantCard) {
            if (amount < DAMAGE_THRESHOLD_M)
                return Enums.RESONANT;
            else if (amount < DAMAGE_THRESHOLD_L)
                return Enums.RESONANT_M;
            else
                return Enums.RESONANT_L;
        }
        if (damageModList.size() == 1) {
            elenum ele = damageModList.get(0);
            if (ele == ICE) {
                if (amount < DAMAGE_THRESHOLD_M)
                    return Enums.ICE;
                else if (amount < DAMAGE_THRESHOLD_L)
                    return Enums.ICE_M;
                else
                    return Enums.ICE_L;
            }
            else if (ele == FORCE) {
                if (amount < DAMAGE_THRESHOLD_M)
                    return Enums.FORCE;
                else if (amount < DAMAGE_THRESHOLD_L)
                    return Enums.FORCE_M;
                else
                    return Enums.FORCE_L;
            }
            else if (ele == FIRE) {
                if (amount < DAMAGE_THRESHOLD_M)
                    return Enums.SOUL_FIRE;
                else if (amount < DAMAGE_THRESHOLD_L)
                    return Enums.SOUL_FIRE_M;
                else
                    return Enums.SOUL_FIRE_L;
            }
            else if (ele == DARK) {
                boolean lower = false;
                for (AbstractMonster m : getEnemies()) {
                    if (m instanceof SpireShield)
                        lower = true;
                    else if (m instanceof SpireSpear)
                        lower = true;
                }
                if (amount < DAMAGE_THRESHOLD_M)
                    return Enums.ELDRITCH;
                else if (amount < DAMAGE_THRESHOLD_L || lower)
                    return Enums.ELDRITCH_M;
                else
                    return Enums.ELDRITCH_L;
            }
            else if (ele == LIGHTNING) {
                if (amount < DAMAGE_THRESHOLD_M)
                    return AbstractGameAction.AttackEffect.LIGHTNING;
                else if (amount < DAMAGE_THRESHOLD_L || adp().hasPower(SurroundedPower.POWER_ID))
                    return Enums.LIGHTNING_M;
                else
                    return Enums.LIGHTNING_L;
            }
            else {
                return getBluntEffect();
            }
        } else if (damageModList.size() > 1) {
            if (amount < DAMAGE_THRESHOLD_M)
                return Enums.DARK_WAVE;
            else if (amount < DAMAGE_THRESHOLD_L)
                return Enums.DARK_WAVE_M;
            else
                return Enums.DARK_WAVE_L;
        }
        else {
            int x = MathUtils.random(0, 1);
            if (x == 1)
                return getSlashEffect();
            else
                return getBluntEffect();
        }
    }

    protected int getDamageForVFX() {
        int amount = damage;
        if (isMultiDamage && multiDamage.length > 0) {
            amount = multiDamage[0];
            for (int x : multiDamage)
                if (x > amount)
                    amount = x;
        }
        return amount;
    }

    public AbstractGameAction.AttackEffect getBluntEffect() {
        int amount = getDamageForVFX();
        if (amount < DAMAGE_THRESHOLD_M)
            return AbstractGameAction.AttackEffect.BLUNT_LIGHT;
        else if (amount < DAMAGE_THRESHOLD_L)
            return AbstractGameAction.AttackEffect.BLUNT_HEAVY;
        else
            return Enums.BLUNT_MASSIVE;
    }

    public AbstractGameAction.AttackEffect getSlashEffect() {
        int amount = getDamageForVFX();
        if (amount < DAMAGE_THRESHOLD_M)
            return getRandomSlash();
        else if (amount < DAMAGE_THRESHOLD_L)
            return AbstractGameAction.AttackEffect.SLASH_HEAVY;
        else
            return Enums.SLASH_MASSIVE;
    }

    public void dmg(AbstractMonster m) {
        dmg(m, getAttackEffect(), false);
    }

    public void dmg(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        dmg(m, fx, false);
    }

    public void dmgTop(AbstractMonster m) {
        dmg(m, getAttackEffect(),  true);
    }

    public void dmgTop(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        dmg(m, fx, true);
    }

    public void allDmg() {
        dmg(null, getAttackEffect(), false);
    }

    public void allDmg(AbstractGameAction.AttackEffect fx) {
        dmg(null, fx, false);
    }

    public void allDmgTop() { dmg(null, getAttackEffect(), true); }

    public void allDmgTop(AbstractGameAction.AttackEffect fx) {
        dmg(null, fx, true);
    }

    public void dmg(AbstractMonster m, AbstractGameAction.AttackEffect fx, boolean top) {
        if (m == null) {
            if (top)
                att(new AttackAction(multiDamage, fx));
            else
                atb(new AttackAction(multiDamage, fx));
            return;
        }
        DamageInfo info = new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL);
        if (top)
            att(new AttackAction(m, info, fx));
        else
            atb(new AttackAction(m, info, fx));
    }

    public void blck() {
        atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
    }

    protected void blckTop() {
        att(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
    }

    protected void upMagic(int x) {upgradeMagicNumber(x);}

    protected void upMagic2(int x) {upgradeSecondMagic(x);}

    protected void upMagic3(int x) {upgradeThirdMagic(x);}

    public void triggerOnDeath() {}

    // Thanks CustomCard renderTitle
    @Override
    protected void renderTitle(SpriteBatch sb) {
        initializeTitle();
        Color renderColor = (Color) ReflectionHacks.getPrivate(this, AbstractCard.class, "renderColor");
        boolean useSmallTitleFont = (boolean) ReflectionHacks.getPrivate(this, AbstractCard.class, "useSmallTitleFont");
        if (isLocked) {
            FontHelper.cardTitleFont.getData().setScale(drawScale);
            FontHelper.renderRotatedText(sb, FontHelper.cardTitleFont, LOCKED_STRING, current_x, current_y,
                    0.0F, 175.0F * drawScale * Settings.scale, angle, false, renderColor);
        } else if (!isSeen) {
            FontHelper.cardTitleFont.getData().setScale(drawScale);
            FontHelper.renderRotatedText(sb, FontHelper.cardTitleFont, UNKNOWN_STRING, current_x, current_y,
                    0.0F, 175.0F * drawScale * Settings.scale, angle, false, renderColor);
        } else {
            if (!useSmallTitleFont)
                FontHelper.cardTitleFont.getData().setScale(drawScale);
            else
                FontHelper.cardTitleFont.getData().setScale(drawScale * 0.85F);

            if (upgraded) {
                Color color = Settings.GREEN_TEXT_COLOR.cpy();
                color.a = renderColor.a;
                FontHelper.renderRotatedText(sb, FontHelper.cardTitleFont, name, current_x, current_y,
                        0.0F, 175.0F * drawScale * Settings.scale, angle, false, color);
            } else {
                FontHelper.renderRotatedText(sb, FontHelper.cardTitleFont, name, current_x, current_y,
                        0.0F, 175.0F * drawScale * Settings.scale, angle, false, renderColor);
            }
        }
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractExileCard card = (AbstractExileCard) super.makeStatEquivalentCopy();
        card.baseMagicNumber = baseMagicNumber;
        card.magicNumber = magicNumber;
        card.secondMagic = secondMagic;
        card.baseSecondMagic = baseSecondMagic;
        card.upgradedSecondMagic = upgradedSecondMagic;
        card.thirdMagic = thirdMagic;
        card.baseThirdMagic = baseThirdMagic;
        card.upgradedThirdMagic = upgradedThirdMagic;
        card.sigil = sigil;
        card.selfRetain = selfRetain;
        card.magicOneIsDebuff = magicOneIsDebuff;
        card.magicTwoIsDebuff = magicTwoIsDebuff;
        card.debuffIncrease = debuffIncrease;
        card.beingDiscarded = beingDiscarded;

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
        obj.elements.addAll(damageModList);
        obj.sigil = sigil;
        obj.retain = selfRetain;
        obj.debuffIncrease = debuffIncrease;
        obj.baseDamage = baseDamage;
        obj.baseBlock = baseBlock;
        obj.baseMagic = baseMagicNumber;
        obj.baseSecondMagic = baseSecondMagic;
        obj.baseThirdMagic = baseThirdMagic;
        return obj;
    }

    @Override
    public void onLoad(CardSaveObject obj) {
        damageModList.clear();
        DamageModifierManager.clearModifiers(this);
        if (adp() == null || !adp().hasRelic(ManaPurifier.ID)) {
            for (elenum ele : obj.elements)
                addModifier(ele);
        }
        sigil = obj.sigil;
        if (sigil)
            cost = -2;
        selfRetain = obj.retain;
        debuffIncrease = obj.debuffIncrease;
        baseDamage = obj.baseDamage;
        baseBlock = obj.baseBlock;
        baseMagicNumber = obj.baseMagic;
        magicNumber = baseMagicNumber;
        baseSecondMagic = obj.baseSecondMagic;
        secondMagic = baseSecondMagic;
        baseThirdMagic = obj.baseThirdMagic;
        thirdMagic = baseThirdMagic;
    }

    @Override
    public Type savedType() {
        return new TypeToken<CardSaveObject>(){}.getType();
    }
}