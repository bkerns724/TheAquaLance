package theExile.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.cards.cardUtil.Resonance;

public abstract class AbstractResonantCard extends AbstractExileCard {
    public Resonance resonance;
    public int extraDraw = 0;
    public int extraEnergy = 0;

    public AbstractResonantCard(String id, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(id, cost, type, rarity, target);
        resonance = new Resonance(baseDamage);
        setResonance();
        initializeDescription();
    }

    protected abstract void setResonance();

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        resonance.resonanceEffects(this, m);
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
        if (resonance != null) {
            baseDamage = resonance.getDamage();
            baseBlock = resonance.block;
            rawDescription = resonance.getDescription();
            overrideRawDesc = true;
        } else {
            rawDescription = "Error";
            overrideRawDesc = false;
        }
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