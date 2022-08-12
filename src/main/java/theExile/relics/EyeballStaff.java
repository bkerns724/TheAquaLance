package theExile.relics;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.RelicWithButton;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theExile.TheExile;
import theExile.patches.ScryPatch;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;

import static theExile.util.Wiz.*;

public class EyeballStaff extends AbstractExileRelic implements RelicWithButton {
    public static final String ID = makeID(EyeballStaff.class.getSimpleName());
    private static final String textureString = "exilemodResources/images/ui/ScryButton.png";
    public static final int SCRY_AMOUNT = 4;

    public EyeballStaff() {
        super(ID, RelicTier.RARE, LandingSound.SOLID, TheExile.Enums.EXILE_BLARPLE_COLOR);
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
        return ImageMaster.loadImage(textureString);
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
