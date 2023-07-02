package theExile.cards;

import theExile.powers.ChargePower;
import theExile.powers.EnergizedExilePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Charge extends AbstractExileCard {
    public final static String ID = makeID(Charge.class.getSimpleName());
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 1;

    public Charge() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new EnergizedExilePower(1));
        applyToSelf(new ChargePower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}