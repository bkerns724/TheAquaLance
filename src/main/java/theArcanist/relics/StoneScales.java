package theArcanist.relics;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.RelicWithButton;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theArcanist.TheArcanist;
import theArcanist.powers.StoneskinPower;

import java.util.ArrayList;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class StoneScales extends AbstractArcanistRelic implements RelicWithButton {
    public static final String ID = makeID("StoneScales");
    public static final String textureString = "arcanistmodResources/images/ui/StoneSkinButton.png";
    public static final int STONESKIN_AMOUNT = 3;

    public StoneScales() {
        super(ID, RelicTier.RARE, LandingSound.HEAVY, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
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
