package theArcanist.relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import theArcanist.TheArcanist;
import theArcanist.powers.JinxThornsPower;

import static theArcanist.util.Wiz.*;
import static theArcanist.ArcanistMod.makeID;

public class DarkClover extends AbstractClickRelic {
    public static final String ID = makeID("DarkClover");
    public static final String textureString = "arcanistmodResources/images/ui/CloverButton.png";
    public static final int JINX_THORNS_AMOUNT = 2;

    public DarkClover() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR, textureString);
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
        applyToSelf(new JinxThornsPower(adp(), JINX_THORNS_AMOUNT));
        grayscale = true;
    }
}
