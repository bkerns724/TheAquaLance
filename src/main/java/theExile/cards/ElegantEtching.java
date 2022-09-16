package theExile.cards;

import theExile.powers.ElegantEtchingPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class ElegantEtching extends AbstractExileCard {
    public final static String ID = makeID(ElegantEtching.class.getSimpleName());
    private final static int COST = 3;
    private final static int UPGRADED_COST = 2;

    public ElegantEtching() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void applyAttributes() {
    }

    public void nonTargetUse() {
        applyToSelf(new ElegantEtchingPower(1));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}