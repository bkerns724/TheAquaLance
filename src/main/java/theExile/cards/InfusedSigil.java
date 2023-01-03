package theExile.cards;

import theExile.actions.InfusedAction;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class InfusedSigil extends AbstractExileCard {
    public final static String ID = makeID(InfusedSigil.class.getSimpleName());
    private final static int COST = -2;

    public InfusedSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        exhaust = true;
        sigil = true;
    }

    @Override
    public void nonTargetUse() {
        Wiz.atb(new InfusedAction());
    }

    public void upp() {
        exhaust = false;
    }
}