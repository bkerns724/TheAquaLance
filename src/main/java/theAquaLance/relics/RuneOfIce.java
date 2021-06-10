package theAquaLance.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.PowerTip;

import static theAquaLance.AquaLanceMod.*;

public class RuneOfIce extends CardPreviewRelic {
    public static final String ID = makeID("RuneOfIce");
    public static final String IMG_PATH = makeRelicPath(ID.replace(modID + ":", "") + ".png");
    public static final String OUTLINE_IMG_PATH = makeRelicPath(ID.replace(modID + ":", "") + "_outline.png");
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.CLINK;
    public static final int BONUS_DAMAGE = 1;

    public RuneOfIce() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
        tips.clear();
        tips.add(new PowerTip(name, flavorText));
    }

    // relic hooks only apply to unblocked damage, so I put the code in Hobble Power

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + BONUS_DAMAGE + DESCRIPTIONS[1];
    }
}
