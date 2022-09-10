package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class SickBurn extends AbstractExileCard {
    public final static String ID = makeID(SickBurn.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public SickBurn() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m, getFireEffect(getDamageForVFX()));
        applyToEnemy(m, new PoisonPower(m, adp(), magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upMagic(UPGRADE_MAGIC);
    }
}