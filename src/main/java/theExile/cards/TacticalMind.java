package theExile.cards;

import theExile.powers.TacticalMindPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class TacticalMind extends AbstractExileCard {
    public final static String ID = makeID(TacticalMind.class.getSimpleName());
    private final static int COST = 1;
    private final static int MAGIC = 1;
    private final static int UPGRADED_COST = 0;

    public TacticalMind() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        Wiz.applyToSelf(new TacticalMindPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}