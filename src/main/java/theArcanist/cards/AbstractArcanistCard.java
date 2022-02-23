package theArcanist.cards;

import basemod.AutoAdd;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import theArcanist.TheArcanist;
import theArcanist.patches.ColoredDamagePatch;
import theArcanist.patches.ColoredDamagePatch.DamageActionColorField;
import theArcanist.util.CardArtRoller;

import java.util.ArrayList;

import static theArcanist.ArcanistMod.*;
import static theArcanist.util.Wiz.*;

@AutoAdd.Ignore
public abstract class AbstractArcanistCard extends CustomCard {
    protected final CardStrings cardStrings;

    public int secondMagic;
    public int baseSecondMagic;
    public boolean upgradedSecondMagic;
    public boolean isSecondMagicModified;

    private float rotationTimer = 0;
    protected int previewIndex;
    protected ArrayList<AbstractCard> cardToPreview = new ArrayList<>();

    private boolean needsArtRefresh = false;

    private static final Color FLAVOR_BOX_COLOR = new Color(90/255f, 0f, 140/255f, 1f);
    private static final Color FLAVOR_TEXT_COLOR = new Color(1.0F, 0.9725F, 0.8745F, 1.0F);

    public AbstractArcanistCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        this(cardID, cost, type, rarity, target, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
    }

    public AbstractArcanistCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(cardID, "", getCardTextureString(cardID.replace(modID + ":", ""), type),
                cost, "", type, color, rarity, target);
        cardStrings = CardCrawlGame.languagePack.getCardStrings(this.cardID);
        rawDescription = cardStrings.DESCRIPTION;
        name = originalName = cardStrings.NAME;

        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);

        initializeTitle();
        initializeDescription();

        if (textureImg.contains("ui/missing.png")) {
            if (CardLibrary.getAllCards() != null && !CardLibrary.getAllCards().isEmpty()) {
                CardArtRoller.computeCard(this);
            } else
                needsArtRefresh = true;
        }
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

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upp();
            if (cardStrings.UPGRADE_DESCRIPTION != null)
                rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public abstract void upp();

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
        DamageActionColorField.fadeSpeed.set(damageAction, ColoredDamagePatch.FadeSpeed.MODERATE);
        atb(damageAction);
    }

    public void dmgTop(AbstractCreature m, AbstractGameAction.AttackEffect fx) {
        att(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL), fx));
    }

    public void allDmg(AbstractGameAction.AttackEffect fx) {
        atb(new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage, DamageInfo.DamageType.NORMAL, fx));
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
        return card;
    }
}
