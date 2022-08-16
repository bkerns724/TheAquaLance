package theExile.relics;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.RelicWithButton;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theExile.TheExile;
import theExile.powers.ResonantCostZeroPower;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class TuningFork extends AbstractExileRelic implements RelicWithButton {
    public static final String ID = makeID(TuningFork.class.getSimpleName());
    private static final String TEXTURE_STRING = "exilemodResources/images/ui/ResonantButton.png";

    public TuningFork() {
        super(ID, RelicTier.UNCOMMON, LandingSound.MAGICAL, TheExile.Enums.EXILE_BROWN_COLOR);
        setUpdatedDescription();
    }

    @Override
    public Texture getTexture() {
        return ImageMaster.loadImage(TEXTURE_STRING);
    }

    @Override
    public void onButtonPress() {
        if (!grayscale) {
            applyToSelf(new ResonantCostZeroPower(1));
            grayscale = true;
        }
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
    public void atPreBattle() {
        grayscale = false;
    }

    @Override
    public void onVictory() {
        grayscale = false;
    }
}
