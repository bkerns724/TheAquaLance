package theArcanist.relics;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.RelicWithButton;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theArcanist.TheArcanist;
import theArcanist.patches.ScryPatch;

import java.util.ArrayList;

import static theArcanist.ArcanistMod.makeID;

import static theArcanist.util.Wiz.*;

public class EyeballStaff extends AbstractArcanistRelic implements RelicWithButton {
    public static final String ID = makeID("EyeballStaff");
    private static final String textureString = "arcanistmodResources/images/ui/ScryButton.png";
    public static final int SCRY_AMOUNT = 3;

    public EyeballStaff() {
        super(ID, RelicTier.RARE, LandingSound.SOLID, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
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
    public Texture getTexture() {
        return new Texture(textureString);
    }

    @Override
    public boolean isButtonDisabled() {
        return grayscale;
    }

    @Override
    public ArrayList<PowerTip> getHoverTips() {
        return tips;
    }

    @Override
    public void onButtonPress() {
        CardCrawlGame.sound.play("UI_CLICK_1");
        flash();
        ScryAction scryAction = new ScryAction(SCRY_AMOUNT);
        ScryPatch.DiscardTriggerField.triggerDiscard.set(scryAction, true);
        atb(scryAction);
        grayscale = true;
    }
}
