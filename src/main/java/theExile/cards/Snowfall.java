package theExile.cards;

import theExile.powers.SnowfallPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class Snowfall extends AbstractExileCard {
    public final static String ID = makeID(Snowfall.class.getSimpleName());
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 1;

    public Snowfall() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        Wiz.applyToSelf(new SnowfallPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}