package theExile.cards;

import static theExile.ExileMod.makeID;

public class SonicAgony extends AbstractResonantCard {
    public final static String ID = makeID(SonicAgony.class.getSimpleName());
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 1;

    public SonicAgony() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
    }

    @Override
    protected void setResonance() {
        resonance.damage = baseDamage;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        resonance.damage += UPGRADE_DAMAGE;
    }
}