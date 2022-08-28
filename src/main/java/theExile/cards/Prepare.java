package theExile.cards;

import theExile.ExileMod;
import theExile.powers.DiscardNextTurnPower;
import theExile.powers.DrawNextTurnPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Prepare extends AbstractExileCard {
    public final static String ID = makeID(Prepare.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 0;

    // SKILL, UNIQUE, SELF
    public Prepare() {
        super(ID, COST, CardType.SKILL, ExileMod.Enums.UNIQUE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void nonTargetUse() {
        applyToSelf(new DrawNextTurnPower(magicNumber));
        applyToSelf(new DiscardNextTurnPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}