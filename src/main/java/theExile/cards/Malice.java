package theExile.cards;

import theExile.powers.SpiritPressurePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Malice extends AbstractExileCard {
    public final static String ID = makeID(Malice.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public Malice() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void nonTargetUse() {
        applyToSelf(new SpiritPressurePower(magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}