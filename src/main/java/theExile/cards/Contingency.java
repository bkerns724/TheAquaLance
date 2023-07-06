package theExile.cards;

import theExile.powers.ContingencyPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Contingency extends AbstractExileCard {
    public final static String ID = makeID(Contingency.class.getSimpleName());
    private final static int COST = 1;
    private final static int UPGRADED_COST = 0;

    public Contingency() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new ContingencyPower(1));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}