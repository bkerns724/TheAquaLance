package theArcanist.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theArcanist.TheArcanist;
import theArcanist.patches.EnergyChangeSubscriber;

import static theArcanist.ArcanistMod.makeID;

import static theArcanist.util.Wiz.*;

public class BootsOfSpeed extends AbstractArcanistClickRelic implements EnergyChangeSubscriber {
    public static final String ID = makeID(BootsOfSpeed.class.getSimpleName());
    private static final String TEXTURE_STRING = "arcanistmodResources/images/ui/DrawButton.png";
    public static final int DRAW_AMOUNT = 3;

    public BootsOfSpeed() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR, TEXTURE_STRING);
        amount = DRAW_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void onChangeEnergy() {
        int energy = EnergyPanel.getCurrentEnergy();
        getElement().elementGrayscale = (energy == 0);
    }

    @Override
    public void buttonPress() {
        // Button shouldn't be pressable at 0 energy, but lets make it robust
        if (EnergyPanel.getCurrentEnergy() == 0)
            return;
        CardCrawlGame.sound.play("UI_CLICK_1");
        flash();
        EnergyPanel.useEnergy(1);
        atb(new DrawCardAction(DRAW_AMOUNT));
    }
}
