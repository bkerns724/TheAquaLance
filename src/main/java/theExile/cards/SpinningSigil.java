package theExile.cards;

import theExile.powers.SpinningSigilPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class SpinningSigil extends AbstractExileCard {
    public final static String ID = makeID(SpinningSigil.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = -2;

    public SpinningSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new SpinningSigilPower(magicNumber));
        draw(1);
        discard(1);
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}