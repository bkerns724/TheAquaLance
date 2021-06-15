package theAquaLance.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static theAquaLance.AquaLanceMod.*;
import static theAquaLance.util.Wiz.*;

public class RuneOfLivingWater extends CardPreviewRelic {
    public static final String ID = makeID("RuneOfLivingWater");
    public static final String IMG_PATH = makeRelicPath(ID.replace(modID + ":", "") + ".png");
    public static final String OUTLINE_IMG_PATH = makeRelicPath(ID.replace(modID + ":", "") + "_outline.png");
    private static final RelicTier TIER = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.MAGICAL;
    private static final int VIGOR_AMT = 6;

    public RuneOfLivingWater() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
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
    public void atTurnStart() {
        counter = 1;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (target != adp() && counter > 0) {
            counter = 0;
            applyToSelf(new VigorPower(adp(), VIGOR_AMT));
        }
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + VIGOR_AMT + DESCRIPTIONS[1];
    }

    public boolean CanSpawn() {return adp().hasRelic(RuneOfIce.ID);}
}