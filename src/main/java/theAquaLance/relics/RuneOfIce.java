package theAquaLance.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theAquaLance.cards.FlashFreeze;

import static theAquaLance.AquaLanceMod.*;
import static theAquaLance.util.Wiz.*;

public class RuneOfIce extends CardPreviewRelic {
    public static final String ID = makeID("RuneOfIce");
    public static final String IMG_PATH = makeRelicPath(ID.replace(modID + ":", "") + ".png");
    public static final String OUTLINE_IMG_PATH = makeRelicPath(ID.replace(modID + ":", "") + "_outline.png");
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public RuneOfIce() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
        cardsToPreview.add(new FlashFreeze());
    }

    @Override
    public void atBattleStart() {
        flash();
        atb(new MakeTempCardInHandAction(new FlashFreeze()));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
