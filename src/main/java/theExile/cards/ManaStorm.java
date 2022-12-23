package theExile.cards;

import theExile.powers.ManaStormPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class ManaStorm extends AbstractExileCard {
    public final static String ID = makeID(ManaStorm.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int UPGRADED_COST = 0;
    private final static int COST = 1;

    public ManaStorm() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new ManaStormPower(magicNumber));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}