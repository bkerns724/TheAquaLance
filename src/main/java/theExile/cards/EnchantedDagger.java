package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.relics.VialOfBlackBlood;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.*;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.getSlashEffect;

public class EnchantedDagger extends AbstractExileCard {
    public final static String ID = makeID(EnchantedDagger.class.getSimpleName());
    private final static int DAMAGE = 12;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int COST = 1;
    public ArrayList<elenum> stableList;

    public EnchantedDagger() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        stableList = new ArrayList<>();
    }

    public void singleTargetUse(AbstractMonster m) {
        if (damageModList.isEmpty())
            dmg(m, getSlashEffect(damage));
        else
            dmg(m);
    }

    @Override
    public void didDiscard() {
        applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        damageModList.clear();
        DamageModifierManager.clearModifiers(this);
        addModifier(stableList, true);

        for (AbstractCard card : adp().hand.group) {
            if (card instanceof AbstractExileCard && card != this) {
                for (elenum e : ((AbstractExileCard) card).damageModList) {
                    if (e == ICE || e == FAKE_ICE)
                        addModifier(ICE);
                    if (e == ELDRITCH || e == FAKE_ELDRITCH)
                        addModifier(ELDRITCH);
                    if (e == LIGHTNING || e == FAKE_LIGHTNING)
                        addModifier(LIGHTNING);
                    if (e == FORCE || e == FAKE_FORCE)
                        addModifier(FORCE);
                }
            }
        }

        super.calculateCardDamage(mo);
        initializeDescription();
    }

    @Override
    public void applyPowers() {
        damageModList.clear();
        DamageModifierManager.clearModifiers(this);
        if (adp() == null)
            return;
        addModifier(stableList, true);

        if (adp().hand.group.contains(this) || adp().limbo.group.contains(this)) {
            for (AbstractCard card : adp().hand.group) {
                if (card instanceof AbstractExileCard && card != this) {
                    for (elenum e : ((AbstractExileCard) card).damageModList) {
                        if (e == ICE || e == FAKE_ICE)
                            addModifier(ICE);
                        if (e == ELDRITCH || e == FAKE_ELDRITCH)
                            addModifier(ELDRITCH);
                        if (e == LIGHTNING || e == FAKE_LIGHTNING)
                            addModifier(LIGHTNING);
                        if (e == FORCE || e == FAKE_FORCE)
                            addModifier(FORCE);
                    }
                }
            }
        }

        super.applyPowers();
        initializeDescription();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    public void initializeDescription() {
        if (cardStrings == null)
            cardStrings = CardCrawlGame.languagePack.getCardStrings(this.cardID);
        if (upgraded && cardStrings.UPGRADE_DESCRIPTION != null)
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        else
            rawDescription = cardStrings.DESCRIPTION;

        if (damageModList != null && stableList != null) {
            if (damageModList.contains(ICE) || stableList.contains(ICE))
                rawDescription = rawDescription.replace("!D! ", "!D! " + COLD_STRING + " ");
            if (damageModList.contains(ELDRITCH) || stableList.contains(ELDRITCH))
                rawDescription = rawDescription.replace("!D! ", "!D! " + ELDRITCH_STRING + " ");
            if (damageModList.contains(FORCE) || stableList.contains(FORCE))
                rawDescription = rawDescription.replace("!D! ", "!D! " + FORCE_STRING + " ");
            if (damageModList.contains(LIGHTNING) || stableList.contains(LIGHTNING))
                rawDescription = rawDescription.replace("!D! ", "!D! " + LIGHTNING_STRING + " ");
        }

        if (selfRetain)
            rawDescription = thisCardStrings.EXTENDED_DESCRIPTION[0] + rawDescription;
        if (isInnate)
            rawDescription = thisCardStrings.EXTENDED_DESCRIPTION[11] + rawDescription;
        if (isEthereal)
            rawDescription = thisCardStrings.EXTENDED_DESCRIPTION[10] + rawDescription;
        if (sigil)
            rawDescription = thisCardStrings.EXTENDED_DESCRIPTION[1] + rawDescription;

        if (exhaust || (damageModList != null && damageModList.contains(LIGHTNING)
                && adp() != null && adp().hasRelic(VialOfBlackBlood.ID)))
            rawDescription = rawDescription + thisCardStrings.EXTENDED_DESCRIPTION[7];

        super.initializeDescription();
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = super.makeStatEquivalentCopy();
        EnchantedDagger eCard = (EnchantedDagger)card;
        eCard.stableList = new ArrayList<>();
        eCard.stableList.addAll(stableList);
        return eCard;
    }
}