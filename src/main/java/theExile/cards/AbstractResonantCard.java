package theExile.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theExile.cards.cardUtil.Resonance;
import theExile.powers.AcousticsPower;
import theExile.powers.ResonantCostZeroPower;

import static theExile.util.Wiz.adRoom;
import static theExile.util.Wiz.adp;

public abstract class AbstractResonantCard extends AbstractExileCard {
    public Resonance resonance;

    public AbstractResonantCard(String id, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(id, cost, type, rarity, target);
        resonance = new Resonance();
        setResonance();
        initializeDescription();
    }

    protected abstract void setResonance();

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        resonance.resonanceEffects(this, m);
    }

    @Override
    public boolean freeToPlay() {
        if (super.freeToPlay())
            return true;

        if (adp() != null && AbstractDungeon.currMapNode != null &&
                adRoom().phase == AbstractRoom.RoomPhase.COMBAT &&
                adp().hasPower(ResonantCostZeroPower.POWER_ID))
            return true;

        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        resonance.toPower();
    }

    @Override
    public void applyPowers() {
        baseDamage = resonance.getDamage();
        baseBlock = resonance.block;
        if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID)) {
            target = CardTarget.ALL_ENEMY;
            isMultiDamage = true;
            initializeDescription();
        } else if (resonance.isSingleTarget()) {
            target = CardTarget.ENEMY;
            isMultiDamage = false;
            initializeDescription();
        } else {
            target = CardTarget.SELF;
            isMultiDamage = false;
            initializeDescription();
        }
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        baseDamage = resonance.getDamage();
        baseBlock = resonance.block;
        super.calculateCardDamage(mo);
    }

    @Override
    public void initializeDescription() {
        if (resonance != null && adRoom() != null && adRoom().phase == AbstractRoom.RoomPhase.COMBAT
                && !AbstractDungeon.gridSelectScreen.forUpgrade) {
            baseDamage = resonance.getDamage();
            baseBlock = resonance.block;
            rawDescription = resonance.getDescription();
            overrideRawDesc = true;
        } else
            overrideRawDesc = false;
        super.initializeDescription();
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractResonantCard copy = (AbstractResonantCard) super.makeStatEquivalentCopy();
        copy.resonance = resonance.resClone();
        copy.initializeDescription();
        initializeDescription();
        return copy;
    }

    public void upp() {
    }
}