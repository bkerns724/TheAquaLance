package theExile.cards;

import theExile.powers.DrawNextTurnPower;
import theExile.powers.EnergizedExilePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class FlashbangSigil extends AbstractExileCard {
    public final static String ID = makeID(FlashbangSigil.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 1;
    private final static int COST = -2;

    public FlashbangSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        sigil = true;
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new DrawNextTurnPower(magicNumber));
        applyToSelf(new EnergizedExilePower(secondMagic));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}