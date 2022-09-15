package theExile.cards;

import theExile.actions.ConvertAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class Convert extends AbstractExileCard {
    public final static String ID = makeID(Convert.class.getSimpleName());
    private final static int COST = 1;
    private final static int UPGRADED_COST = 0;

    public Convert() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void applyAttributes() {
        exhaust = true;
    }

    public void nonTargetUse() {
        atb(new ConvertAction());
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}