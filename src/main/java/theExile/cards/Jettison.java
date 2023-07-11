package theExile.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class Jettison extends AbstractExileCard {
    public final static String ID = makeID(Jettison.class.getSimpleName());
    private final static int BLOCK = 4;
    private final static int UPGRADE_BLOCK = 2;
    private final static int COST = 1;

    public Jettison() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseBlock = BLOCK;
        selfRetain = true;
    }

    @Override
    public void nonTargetUse() {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                int count = adp().hand.size();
                for (int i = 0; i < count; i++)
                    att(new GainBlockAction(adp(), block, true));
                att(new DiscardAction(adp(), adp(), count, true));
            }
        });
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}