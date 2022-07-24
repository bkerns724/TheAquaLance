package theExile.cards;

import theExile.ExileMod;

import static theExile.ExileMod.makeID;

public class DeathlyChimes extends AbstractResonantCard {
    public final static String ID = makeID(DeathlyChimes.class.getSimpleName());
    private final static int DAMAGE = 20;
    private final static int UPGRADE_DAMAGE = 5;
    private final static int COST = 1;

    public DeathlyChimes() {
        super(ID, COST, CardType.ATTACK, ExileMod.Enums.UNIQUE, CardTarget.ENEMY);
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