package theExile.cards;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.cDraw;
import static theExile.util.Wiz.discard;

public class FlashbangSigil extends AbstractExileCard {
    public final static String ID = makeID(FlashbangSigil.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = -2;

    public FlashbangSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        sigil = true;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        cDraw(magicNumber);
        discard(magicNumber);
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}