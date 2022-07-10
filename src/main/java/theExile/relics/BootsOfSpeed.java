package theExile.relics;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.RelicWithButton;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.TheExile;
import theExile.patches.EnergyChangeSubscriber;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;

import static theExile.util.Wiz.*;

public class BootsOfSpeed extends AbstractExileRelic implements EnergyChangeSubscriber, RelicWithButton {
    public static final String ID = makeID(BootsOfSpeed.class.getSimpleName());
    private static final String TEXTURE_STRING = "exilemodResources/images/ui/DrawButton.png";
    public static final int DRAW_AMOUNT = 3;
    private int energy;

    public BootsOfSpeed() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = DRAW_AMOUNT;
        energy = EnergyPanel.getCurrentEnergy();
        setUpdatedDescription();
    }

    @Override
    public void onChangeEnergy() {
        energy = EnergyPanel.getCurrentEnergy();
    }

    @Override
    public Texture getTexture() {
        return ImageMaster.loadImage(TEXTURE_STRING);
    }

    @Override
    public boolean isButtonDisabled() {
        return (energy <= 0);
    }

    @Override
    public ArrayList<PowerTip> getHoverTips() {
        return tips;
    }

    @Override
    public void onButtonPress() {
        // Button shouldn't be pressable at 0 energy, but lets make it robust
        if (EnergyPanel.getCurrentEnergy() == 0)
            return;
        CardCrawlGame.sound.play("UI_CLICK_1");
        flash();
        EnergyPanel.useEnergy(1);
        atb(new DrawCardAction(DRAW_AMOUNT));
    }
}
