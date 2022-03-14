package theArcanist.relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import theArcanist.TheArcanist;
import theArcanist.actions.ChaosMagicAction;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class GlowingNecklace extends AbstractClickRelic {
    public static final String ID = makeID("GlowingNecklace");
    private static final String textureString = "arcanistmodResources/images/ui/ChaosButton.png";
    private static final int BUFF_AMOUNT = 1;

    public GlowingNecklace() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR, textureString);
        counter = 0;
        grayscale = true;
        amount = 1;
    }

    @Override
    public void buttonPress() {
        for (int i = 0; i < counter; i++)
            atb(new ChaosMagicAction());
        CardCrawlGame.sound.play("UI_CLICK_1");
        flash();
        counter = 0;
        grayscale = true;
    }

    @Override
    public void onVictory() {
        counter++;
        grayscale = false;
    }
}
