package theExile.cards;

import theExile.powers.ElegantEtchingPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class ElegantEtching extends AbstractExileCard {
    public final static String ID = makeID(ElegantEtching.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 3;

    public ElegantEtching() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void nonTargetUse() {
        applyToSelf(new ElegantEtchingPower(magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}