package theExile.cards;

import theExile.powers.AmplifyPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Amplify extends AbstractExileCard {
    public final static String ID = makeID(Amplify.class.getSimpleName());
    private final static int MAGIC = 6;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 1;

    public Amplify() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new AmplifyPower(magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}