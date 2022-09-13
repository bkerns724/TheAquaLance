package theExile.relics;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.RelicWithButton;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.TheExile;
import theExile.powers.ForceChargePower;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class PlaceholderRelic extends AbstractExileRelic implements RelicWithButton {
    public static final String ID = makeID(PlaceholderRelic.class.getSimpleName());
    private static final String TEXTURE_STRING = "exilemodResources/images/ui/GolemFistButton.png";

    public PlaceholderRelic() {
        super(ID, RelicTier.STARTER, LandingSound.CLINK, TheExile.Enums.EXILE_BROWN_COLOR);
        setUpdatedDescription();
    }

    @Override
    public Texture getTexture() {
        return ImageMaster.loadImage(TEXTURE_STRING);
    }

    @Override
    public boolean isButtonDisabled() {
        if (EnergyPanel.totalCount <= 0)
            return true;
        AbstractPower pow = adp().getPower(ForceChargePower.POWER_ID);
        if (pow == null)
            return false;
        if (adp().hasRelic(ManaCrystal.ID))
            return pow.amount >= ManaCrystal.NEW_LIMIT;
        return true;
    }

    @Override
    public ArrayList<PowerTip> getHoverTips() {
        return tips;
    }

    @Override
    public void onButtonPress() {
        if (!isButtonDisabled()) {
            CardCrawlGame.sound.play("UI_CLICK_1");
            flash();
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    if (EnergyPanel.totalCount > 0) {
                        adp().loseEnergy(1);
                        applyToSelfTop(new ForceChargePower());
                    }
                    isDone = true;
                }
            });
        }
    }
}
