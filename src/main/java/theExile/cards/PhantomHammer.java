package theExile.cards;

import static theExile.ExileMod.makeID;

public class PhantomHammer extends AbstractExileCard {
    public final static String ID = makeID(PhantomHammer.class.getSimpleName());
    private final static int DAMAGE = 15;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 2;

    public PhantomHammer() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.FORCE);
        isMultiDamage = true;
    }

    @Override
    public void nonTargetUse() {
        allDmg();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}