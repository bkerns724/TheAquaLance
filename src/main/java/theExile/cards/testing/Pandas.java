package theExile.cards.testing;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import theExile.cards.AbstractExileCard;
import theExile.orbs.CrazyPanda;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

@AutoAdd.Ignore
public class Pandas extends AbstractExileCard {
    public final static String ID = makeID(Pandas.class.getSimpleName());
    private final static int COST = 0;

    public Pandas() {
        super(ID, COST, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
    }

    public void upp() {
    }

    @Override
    public void nonTargetUse() {
        atb(new IncreaseMaxOrbAction(10));
        for (int i = 0; i < 10; i++)
            atb(new ChannelAction(new CrazyPanda(10)));
    }
}