package theExile.cards;

import theExile.actions.InfusedAction;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class InfusedSigil extends AbstractExileCard {
    public final static String ID = makeID(InfusedSigil.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = -2;

    public InfusedSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        magicNumber = baseMagicNumber = MAGIC;
        sigil = true;
    }

    @Override
    public void nonTargetUse() {
        Wiz.atb(new InfusedAction());
        if (upgraded)
            Wiz.cDraw(magicNumber);
    }

    public void upp() { }
}