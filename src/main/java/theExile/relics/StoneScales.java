package theExile.relics;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.RelicWithButton;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theExile.TheExile;
import theExile.powers.StoneskinPower;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class StoneScales extends AbstractExileRelic implements RelicWithButton {
    public static final String ID = makeID("StoneScales");
    public static final String textureString = "exilemodResources/images/ui/StoneSkinButton.png";
    public static final int STONESKIN_AMOUNT = 3;

    public StoneScales() {
        super(ID, RelicTier.RARE, LandingSound.HEAVY, TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = STONESKIN_AMOUNT;
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
        applyToSelf(new StoneskinPower(adp(), STONESKIN_AMOUNT));
        grayscale = true;
    }
}
