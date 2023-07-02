package theExile.cards;

import theExile.powers.CataclysmPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class CataclysmSigil extends AbstractExileCard {
    public final static String ID = makeID(CataclysmSigil.class.getSimpleName());
    private final static int MAGIC = 50;
    private final static int UPGRADE_MAGIC = 15;
    private final static int COST = -2;

    public CataclysmSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = CataclysmPower.CARD_COUNT;
        sigil = true;
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new CataclysmPower(magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}