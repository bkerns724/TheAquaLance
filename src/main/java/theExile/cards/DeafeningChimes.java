package theExile.cards;

import theExile.ExileMod;

import static theExile.ExileMod.makeID;

public class DeafeningChimes extends AbstractResonantCard {
    public final static String ID = makeID(DeafeningChimes.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int BLOCK = 8;
    private final static int UPGRADE_BLOCK = 3;
    private final static int COST = 1;

    public DeafeningChimes() {
        super(ID, COST, CardType.ATTACK, ExileMod.Enums.UNIQUE, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
    }

    @Override
    protected void setResonance() {
        resonance.damage = baseDamage;
        resonance.block = baseBlock;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        resonance.damage += UPGRADE_DAMAGE;
        upgradeBlock(UPGRADE_BLOCK);
        resonance.block += UPGRADE_BLOCK;
    }
}