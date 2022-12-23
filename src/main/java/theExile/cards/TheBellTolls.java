package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.BellTollsPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class TheBellTolls extends AbstractExileCard {
    public final static String ID = makeID(TheBellTolls.class.getSimpleName());
    private final static int MAGIC = 9;
    private final static int UPGRADE_MAGIC = 3;
    private final static int COST = 3;

    public TheBellTolls() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        applyToEnemy(m, new BellTollsPower(m, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}