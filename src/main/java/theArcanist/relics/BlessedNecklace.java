package theArcanist.relics;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.RelicWithButton;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theArcanist.TheArcanist;
import theArcanist.actions.BlessedNecklaceAction;

import java.util.ArrayList;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.atb;

public class BlessedNecklace extends AbstractArcanistRelic implements RelicWithButton {
    public static final String ID = makeID(BlessedNecklace.class.getSimpleName());
    private static final String textureString = "arcanistmodResources/images/ui/BlessedButton.png";
    private static final int blockAmount = 6;

    public BlessedNecklace() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        setUpdatedDescription();
        amount = blockAmount;
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
        return new Texture(textureString);
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
