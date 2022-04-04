package theArcanist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;

import static theArcanist.util.Wiz.adp;

public class ArcanistGreedAction extends AbstractGameAction {
    private static final float DURATION = 0.2f;

    public ArcanistGreedAction(int amount) {
        this.amount = amount;
        duration = startDuration = DURATION;
    }

    @Override
    public void update() {
        if (duration == startDuration) {
            adp().gainGold(amount);
            AbstractDungeon.effectList.add(new RainingGoldEffect(amount));
        }
        tickDuration();
    }
}
