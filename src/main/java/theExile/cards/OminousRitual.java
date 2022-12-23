package theExile.cards;

import theExile.powers.OminousRitualPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class OminousRitual extends AbstractExileCard {
    public final static String ID = makeID(OminousRitual.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public OminousRitual() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void nonTargetUse() {
        applyToSelf(new OminousRitualPower(magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}