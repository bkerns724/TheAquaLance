package theExile.cards;

import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import theExile.powers.SummonBeesPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class SummonBees extends AbstractExileCard {
    public final static String ID = makeID(SummonBees.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 0;

    public SummonBees() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        atb(new IncreaseMaxOrbAction(magicNumber));
        Wiz.applyToSelf(new SummonBeesPower());
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}