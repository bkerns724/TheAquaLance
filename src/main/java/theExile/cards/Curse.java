package theExile.cards;

import static theExile.ExileMod.makeID;

public class Curse extends AbstractResonantCard {
    public final static String ID = makeID(Curse.class.getSimpleName());
    private final static int DAMAGE = 4;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 2;
    private final static int COST = 1;

    public Curse() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void setResonance() {
        resonance.jinx = baseMagicNumber;
        resonance.damage = DAMAGE;
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        magicOneIsDebuff = true;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        resonance.damage += UPGRADE_DAMAGE;
    }
}