package theExile.cards;

import theExile.powers.VitalityPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Vitality extends AbstractExileCard {
    public final static String ID = makeID(Vitality.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 0;

    public Vitality() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void nonTargetUse() {
        applyToSelf(new VitalityPower(magicNumber));
    }

    public void upp() {
        isInnate = true;
    }
}