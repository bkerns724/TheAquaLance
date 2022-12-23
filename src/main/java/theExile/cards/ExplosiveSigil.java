package theExile.cards;

import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class ExplosiveSigil extends AbstractExileCard {
    public final static String ID = makeID(ExplosiveSigil.class.getSimpleName());
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 2;

    public ExplosiveSigil() {
        super(ID, -2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        isMultiDamage = true;
        sigil = true;
    }

    @Override
    public void nonTargetUse() {
        if (damageModList.isEmpty())
            allDmg(Wiz.getFireEffect(getDamageForVFX()));
        else
            allDmg();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}