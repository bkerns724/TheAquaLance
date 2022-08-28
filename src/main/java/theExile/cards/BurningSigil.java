package theExile.cards;

import static theExile.ExileMod.makeID;

public class BurningSigil extends AbstractExileCard {
    public final static String ID = makeID(BurningSigil.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 3;

    public BurningSigil() {
        super(ID, -2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        isMultiDamage = true;
        sigil = true;
    }

    @Override
    public void nonTargetUse() {
        allDmg();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}