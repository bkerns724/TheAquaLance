package theExile.relics;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.RelicWithButton;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theExile.TheExile;
import theExile.actions.BlessedNecklaceAction;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class BlessedNecklace extends AbstractExileRelic implements RelicWithButton {
    public static final String ID = makeID(BlessedNecklace.class.getSimpleName());
    private static final String textureString = "exilemodResources/images/ui/BlessedButton.png";
    public static final int BLOCK_AMOUNT = 6;

    public BlessedNecklace() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK, TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = BLOCK_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void onEquip() {
        adp().loseRelic(NecklaceOfShielding.ID);
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
        atb(new BlessedNecklaceAction(amount));
        grayscale = true;
    }

    @Override
    public boolean canSpawn() {
        return adp().hasRelic(NecklaceOfShielding.ID);
    }
}
