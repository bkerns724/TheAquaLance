package theExile.cards;

import theExile.powers.PorcupinePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class SummonPorcupine extends AbstractExileCard {
    public final static String ID = makeID(SummonPorcupine.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int COST = 1;
    private final static int UPGRADED_COST = 0;

    public SummonPorcupine() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void nonTargetUse() {
        applyToSelf(new PorcupinePower(1));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}