package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToEnemy;

public class Debilitate extends AbstractExileCard {
    public final static String ID = makeID(Debilitate.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int SECOND_MAGIC = 5;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 2;

    public Debilitate() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void singleTargetUse(AbstractMonster m) {
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        applyToEnemy(m, new PoisonPower(m, adp(), secondMagic));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}