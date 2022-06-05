package theArcanist.relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import theArcanist.TheArcanist;
import theArcanist.powers.StoneskinPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class StoneScales extends AbstractArcanistClickRelic {
    public static final String ID = makeID("StoneScales");
    public static final String textureString = "arcanistmodResources/images/ui/StoneSkinButton.png";
    public static final int STONESKIN_AMOUNT = 3;

    public StoneScales() {
        super(ID, RelicTier.RARE, LandingSound.HEAVY, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR, textureString);
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
    public void buttonPress() {
        CardCrawlGame.sound.play("UI_CLICK_1");
        flash();
        applyToSelf(new StoneskinPower(adp(), STONESKIN_AMOUNT));
        grayscale = true;
    }
}
