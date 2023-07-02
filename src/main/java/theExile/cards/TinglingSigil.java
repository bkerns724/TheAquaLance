package theExile.cards;

import theExile.powers.TinglingPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class TinglingSigil extends AbstractExileCard {
    public final static String ID = makeID(TinglingSigil.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = -1;
    private final static int COST = -2;

    public TinglingSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new TinglingPower(1, magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}