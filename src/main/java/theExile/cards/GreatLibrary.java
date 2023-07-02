package theExile.cards;

import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import theExile.ExileMod;
import theExile.actions.GreatLibraryAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class GreatLibrary extends AbstractExileCard {
    public final static String ID = makeID(GreatLibrary.class.getSimpleName());
    private final static int COST = 1;

    public GreatLibrary() {
        super(ID, COST, CardType.SKILL, ExileMod.Enums.UNIQUE, CardTarget.SELF);
    }

    public void applyAttributes() {
        exhaust = true;
    }

    @Override
    public void nonTargetUse() {
        atb(new ExpertiseAction(adp(), 99));
        atb(new GreatLibraryAction());
    }

    public void upp() {
        selfRetain = true;
    }
}