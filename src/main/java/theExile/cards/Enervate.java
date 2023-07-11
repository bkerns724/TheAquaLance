package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class Enervate extends AbstractExileCard {
    public final static String ID = makeID(Enervate.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public Enervate() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}