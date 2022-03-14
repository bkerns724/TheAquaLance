package theArcanist.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import theArcanist.TheArcanist;

import static theArcanist.util.Wiz.*;
import static theArcanist.ArcanistMod.makeID;

public class VestOfManyPockets extends AbstractClickRelic {
    public static final String ID = makeID("VestOfManyPockets");
    private static final String textureString = "arcanistmodResources/images/ui/CycleButton.png";
    private static final int CYCLE_AMOUNT = 1;

    public VestOfManyPockets() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR, textureString);
        amount = CYCLE_AMOUNT;
    }

    @Override
    public void atBattleStartPreDraw() {
        grayscale = false;
    }

    @Override
    public void onVictory() {
        grayscale = false;
    }

    @Override
    public void buttonPress() {
        CardCrawlGame.sound.play("UI_CLICK_1");
        flash();
        atb(new DrawCardAction(amount));
        discard(amount);
        grayscale = true;
    }
}
