package theExile.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import theExile.ExileMod;
import theExile.actions.EasyXCostAction;
import theExile.orbs.CrazyPanda;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;
import static theExile.util.Wiz.att;

public class SummonPandas extends AbstractExileCard {
    public final static String ID = makeID(SummonPandas.class.getSimpleName());
    private final static int COST = -1;

    public SummonPandas() {
        super(ID, COST, CardType.POWER, ExileMod.Enums.UNIQUE, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
    }

    public void nonTargetUse() {
        atb(new EasyXCostAction(this, (effect, params) -> {
            if (params[0] == 1)
                effect++;

            for (int i = 0; i < effect; i++)
                att(new ChannelAction(new CrazyPanda()));
            att(new IncreaseMaxOrbAction(effect));
            return true;
        }, upgraded ? 1 :0 ));
    }

    public void upp() {
    }
}