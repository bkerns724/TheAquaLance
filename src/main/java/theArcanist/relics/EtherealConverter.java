package theArcanist.relics;

import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import theArcanist.TheArcanist;
import theArcanist.actions.MyAddTempHPAction;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class EtherealConverter extends AbstractArcanistClickRelic {
    public static final String ID = makeID("EtherealConverter");
    private static final String textureString = "arcanistmodResources/images/ui/ConverterButton.png";

    public EtherealConverter() {
        super(ID, RelicTier.RARE, LandingSound.MAGICAL, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR, textureString);
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
        if (adp().currentBlock > 0) {
            CardCrawlGame.sound.play("UI_CLICK_1");
            flash();
            atb(new RemoveAllBlockAction(adp(), adp()));
            atb(new MyAddTempHPAction(adp(), adp(), adp().currentBlock));
            grayscale = true;
        }
    }
}
