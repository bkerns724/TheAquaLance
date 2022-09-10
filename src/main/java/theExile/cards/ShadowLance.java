package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.LifeTapPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class ShadowLance extends AbstractExileCard {
    public final static String ID = makeID(ShadowLance.class.getSimpleName());
    private final static int DAMAGE = 15;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 3;

    public ShadowLance() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.ELDRITCH);
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
        Wiz.applyToEnemy(m, new LifeTapPower(m, magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
        upgradeDamage(UPGRADE_DAMAGE);
    }
}