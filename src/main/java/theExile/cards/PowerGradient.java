package theExile.cards;

import theExile.powers.PowerGradientPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class PowerGradient extends AbstractExileCard {
    public final static String ID = makeID(PowerGradient.class.getSimpleName());
    private final static int COST = 2;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public PowerGradient() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new PowerGradientPower(magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}