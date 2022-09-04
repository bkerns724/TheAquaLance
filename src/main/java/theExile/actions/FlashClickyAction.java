package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theExile.ExileMod;
import theExile.util.ClickableForPower;
import theExile.vfx.FlashClickPowerEffect;

public class FlashClickyAction extends AbstractGameAction {
    private final ClickableForPower clicky;

    public FlashClickyAction(ClickableForPower clicky) {
        this.clicky = clicky;
    }

    @Override
    public void update() {
        AbstractDungeon.effectList.add(new FlashClickPowerEffect(clicky));
        isDone = true;
    }
}
