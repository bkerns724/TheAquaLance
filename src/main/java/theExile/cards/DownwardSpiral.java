package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.getDebuffCount;

public class DownwardSpiral extends AbstractExileCard {
    public final static String ID = makeID(DownwardSpiral.class.getSimpleName());
    private final static int MAGIC = 0;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public DownwardSpiral() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        int count = getDebuffCount(m);
        count += magicNumber;
        applyToEnemy(m, new JinxPower(m, count));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}