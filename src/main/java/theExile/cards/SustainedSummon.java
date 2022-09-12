package theExile.cards;

import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import theExile.ExileMod;
import theExile.powers.SummonMonsterPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class SustainedSummon extends AbstractExileCard {
    public final static String ID = makeID(SustainedSummon.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 4;
    private final static int SECOND_MAGIC = 3;
    private final static int COST = 2;

    public SustainedSummon() {
        super(ID, COST, CardType.POWER, ExileMod.Enums.UNIQUE, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void nonTargetUse() {
        atb(new IncreaseMaxOrbAction(secondMagic));
        applyToSelf(new SummonMonsterPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}