package theExile.cards;

import theExile.ExileMod;
import theExile.powers.HastePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Haste extends AbstractExileCard {
    public final static String ID = makeID(Haste.class.getSimpleName());
    private final static int COST = 1;
    private final static int UPGRADED_COST = 0;

    public Haste() {
        super(ID, COST, CardType.POWER, ExileMod.Enums.UNIQUE, CardTarget.SELF);
    }

    public void applyAttributes() {
    }

    public void nonTargetUse() {
        applyToSelf(new HastePower(1));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}