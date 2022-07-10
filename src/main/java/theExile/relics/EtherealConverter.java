package theExile.relics;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.RelicWithButton;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theExile.TheExile;
import theExile.actions.MyAddTempHPAction;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class EtherealConverter extends AbstractExileRelic implements RelicWithButton {
    public static final String ID = makeID("EtherealConverter");
    private static final String textureString = "exilemodResources/images/ui/ConverterButton.png";

    public EtherealConverter() {
        super(ID, RelicTier.RARE, LandingSound.MAGICAL, TheExile.Enums.EXILE_BLARPLE_COLOR);
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
        if (adp().currentBlock > 0) {
            CardCrawlGame.sound.play("UI_CLICK_1");
            flash();
            atb(new RemoveAllBlockAction(adp(), adp()));
            atb(new MyAddTempHPAction(adp(), adp(), adp().currentBlock));
            grayscale = true;
        }
    }
}
