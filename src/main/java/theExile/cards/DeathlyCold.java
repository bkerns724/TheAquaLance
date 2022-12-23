package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.TempNegStrengthPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class DeathlyCold extends AbstractExileCard {
    public final static String ID = makeID(DeathlyCold.class.getSimpleName());
    private final static int DAMAGE = 3;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 0;

    public DeathlyCold() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.ICE);
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
        applyToEnemy(m, new TempNegStrengthPower(m, magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}