package theArcanist.relics;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelicWithUI;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theArcanist.TheArcanist;
import theArcanist.patches.EnergyChangeSubscriber;

import java.util.ArrayList;

import static theArcanist.ArcanistMod.makeID;

import static theArcanist.util.Wiz.*;

public class BootsOfSpeed extends AbstractArcanistRelic implements EnergyChangeSubscriber, ClickableRelicWithUI {
    public static final String ID = makeID(BootsOfSpeed.class.getSimpleName());
    private static final String TEXTURE_STRING = "arcanistmodResources/images/ui/DrawButton.png";
    public static final int DRAW_AMOUNT = 3;
    private int energy;

    public BootsOfSpeed() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = DRAW_AMOUNT;
        int energy = EnergyPanel.getCurrentEnergy();
        setUpdatedDescription();
    }

    @Override
    public void onChangeEnergy() {
        int energy = EnergyPanel.getCurrentEnergy();
    }

    @Override
    public Texture getTexture() {
        return new Texture(TEXTURE_STRING);
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
