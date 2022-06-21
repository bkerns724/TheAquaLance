package theArcanist.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class NecklaceOfShielding extends AbstractArcanistClickRelic {
    public static final String ID = makeID(NecklaceOfShielding.class.getSimpleName());
    private static final String textureString = "arcanistmodResources/images/ui/StarterButton.png";
    private static final int discardAmount = 1;
    private static final int blockAmount = 4;

    public NecklaceOfShielding() {
        super(ID, RelicTier.STARTER, LandingSound.CLINK, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR, textureString);
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
    public void buttonPress() {
        CardCrawlGame.sound.play("UI_CLICK_1");
        flash();
        atb(new GainBlockAction(adp(), amount2));
        discard(amount);
        grayscale = true;
    }
}
