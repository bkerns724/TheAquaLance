package theExile.relics;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.RelicWithButton;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theExile.TheExile;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class NecklaceOfShielding extends AbstractExileRelic implements RelicWithButton {
    public static final String ID = makeID(NecklaceOfShielding.class.getSimpleName());
    private static final String textureString = "exilemodResources/images/ui/StarterButton.png";
    private static final int discardAmount = 1;
    private static final int blockAmount = 5;

    public NecklaceOfShielding() {
        super(ID, RelicTier.STARTER, LandingSound.CLINK, TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = discardAmount;
        amount2 = blockAmount;
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
        atb(new GainBlockAction(adp(), amount2));
        discard(amount);
        grayscale = true;
    }
}
