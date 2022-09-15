package theExile.cards;

import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class ShrapnelSigil extends AbstractExileCard {
    public final static String ID = makeID(ShrapnelSigil.class.getSimpleName());
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;

    public ShrapnelSigil() {
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
        if (damageModList.isEmpty())
            allDmg(Wiz.getSlashEffect(getDamageForVFX()));
        else
            allDmg();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}