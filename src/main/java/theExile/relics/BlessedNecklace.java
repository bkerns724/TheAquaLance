package theExile.relics;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.RelicWithButton;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theExile.TheExile;
import theExile.util.Wiz;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class BlessedNecklace extends AbstractExileRelic implements RelicWithButton {
    public static final String ID = makeID(BlessedNecklace.class.getSimpleName());
    private static final String textureString = "exilemodResources/images/ui/BlessedButton.png";
    public static final int BLOCK_AMOUNT = 10;
    public static final int DRAW_AMOUNT = 3;

    public BlessedNecklace() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK, TheExile.Enums.EXILE_BROWN_COLOR);
        amount = BLOCK_AMOUNT;
        amount2 = DRAW_AMOUNT;
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
        atb(new GainBlockAction(adp(), BLOCK_AMOUNT));
        Wiz.draw(DRAW_AMOUNT);
        grayscale = true;
    }

    @Override
    public boolean canSpawn() {
        return adp().hasRelic(NecklaceOfShielding.ID);
    }

    public void obtain() {
        if (adp().hasRelic(NecklaceOfShielding.ID)) {
            for (int i = 0; i < adp().relics.size(); i++) {
                if ((adp().relics.get(i)).relicId.equals(NecklaceOfShielding.ID)) {
                    instantObtain(adp(), i, true);
                    break;
                }
            }
        } else
            super.obtain();
    }
}
