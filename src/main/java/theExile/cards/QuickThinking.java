package theExile.cards;

import com.megacrit.cardcrawl.powers.DrawPower;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class QuickThinking extends AbstractExileCard {
    public final static String ID = makeID(QuickThinking.class.getSimpleName());
    private final static int COST = 1;
    private final static int UPGRADED_COST = 0;

    public QuickThinking() {
        super(ID, COST, CardType.POWER, ExileMod.Enums.UNIQUE, CardTarget.SELF);
    }

    public void applyAttributes() {

    }

    public void nonTargetUse() {
        applyToSelf(new DrawPower(adp(), 1));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}