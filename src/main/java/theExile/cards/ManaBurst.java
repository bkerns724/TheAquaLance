package theExile.cards;

import theExile.powers.ManaBurstPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class ManaBurst extends AbstractExileCard {
    public final static String ID = makeID(ManaBurst.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int UPGRADED_COST = 1;
    private final static int COST = 2;

    public ManaBurst() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void nonTargetUse() {
        applyToSelf(new ManaBurstPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}