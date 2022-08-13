package theExile.cards;

import theExile.ExileMod;

import static theExile.ExileMod.makeID;

public class LethalChimes extends AbstractResonantCard {
    public final static String ID = makeID(LethalChimes.class.getSimpleName());
    private final static int DAMAGE = 15;
    private final static int UPGRADE_DAMAGE = 5;
    private final static int COST = 1;

    public LethalChimes() {
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