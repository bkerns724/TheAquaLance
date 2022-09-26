package theExile.cards;

import theExile.powers.RelentlessnessPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Relentlessness extends AbstractExileCard {
    public final static String ID = makeID(Relentlessness.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int UPGRADED_COST = 0;
    private final static int COST = 1;

    public Relentlessness() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new RelentlessnessPower(magicNumber));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}