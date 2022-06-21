package theArcanist.relics;

import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import theArcanist.TheArcanist;
import theArcanist.patches.ScryPatch;

import static theArcanist.ArcanistMod.makeID;

import static theArcanist.util.Wiz.*;

public class EyeballStaff extends AbstractArcanistClickRelic {
    public static final String ID = makeID("EyeballStaff");
    private static final String textureString = "arcanistmodResources/images/ui/ScryButton.png";
    public static final int SCRY_AMOUNT = 3;

    public EyeballStaff() {
        super(ID, RelicTier.RARE, LandingSound.SOLID, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR, textureString);
        amount = SCRY_AMOUNT;
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
        ScryAction scryAction = new ScryAction(SCRY_AMOUNT);
        ScryPatch.DiscardTriggerField.triggerDiscard.set(scryAction, true);
        atb(scryAction);
        grayscale = true;
    }
}
