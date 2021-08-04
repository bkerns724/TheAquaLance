package theAquaLance.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theAquaLance.cards.FlashFreeze;
import theAquaLance.cards.Flow;

import static theAquaLance.AquaLanceMod.*;
import static theAquaLance.util.Wiz.*;

public class RuneOfLivingWater extends CardPreviewRelic {
    public static final String ID = makeID("RuneOfLivingWater");
    public static final String IMG_PATH = makeRelicPath(ID.replace(modID + ":", "") + ".png");
    public static final String OUTLINE_IMG_PATH = makeRelicPath(ID.replace(modID + ":", "") + "_outline.png");
    private static final RelicTier TIER = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.MAGICAL;

    public RuneOfLivingWater() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
        cardsToPreview.add(new FlashFreeze());
        cardsToPreview.add(new Flow());
    }

    public void obtain() {
        // Below statement should always be true if this relic is being obtained.
        if (AbstractDungeon.player.hasRelic(RuneOfIce.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); i++) {
                if ((AbstractDungeon.player.relics.get(i)).relicId.equals(RuneOfIce.ID)) {
                    instantObtain(AbstractDungeon.player, i,true);
                    break;
                }
            }
        }
    }

    @Override
    public void atBattleStart() {
        flash();
        atb(new MakeTempCardInHandAction(new FlashFreeze()));
        atb(new MakeTempCardInHandAction(new Flow()));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public boolean canSpawn() {
        return adp().hasRelic(RuneOfIce.ID);
    }
}