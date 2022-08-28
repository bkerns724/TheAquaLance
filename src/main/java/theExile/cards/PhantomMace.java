package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;

public class PhantomMace extends AbstractExileCard {
    public final static String ID = makeID(PhantomMace.class.getSimpleName());
    private final static int DAMAGE = 16;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int COST = 2;

    public PhantomMace() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.FORCE);
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}