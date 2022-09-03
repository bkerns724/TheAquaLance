package theExile.cards;

import theExile.powers.SlipperyPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class SlipperySigil extends AbstractExileCard {
    public final static String ID = makeID(SlipperySigil.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = -2;

    public SlipperySigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
        exhaust = true;
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new SlipperyPower(magicNumber));
    }

    public void upp() {
        exhaust = false;
    }
}