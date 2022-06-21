package theArcanist.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.atb;
import static theArcanist.util.Wiz.discard;

public class VestOfManyPockets extends AbstractArcanistClickRelic {
    public static final String ID = makeID(VestOfManyPockets.class.getSimpleName());
    private static final String textureString = "arcanistmodResources/images/ui/CycleButton.png";
    private static final int CYCLE_AMOUNT = 1;

    public VestOfManyPockets() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR, textureString);
        amount = CYCLE_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void atPreBattle() {
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
