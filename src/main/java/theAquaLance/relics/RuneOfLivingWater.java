package theAquaLance.relics;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theAquaLance.TheAquaLance;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class RuneOfLivingWater extends AbstractEasyRelic {
    public static final String ID = makeID("RuneOfLivingWater");
    private static final int CHARGE_COUNT = 2;
    private static final int DRAW_AMOUNT = 1;
    private static final int DISCARD_AMOUNT = 1;
    private static final int SHUFFLE_CHARGE = 1;

    public RuneOfLivingWater() {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL, TheAquaLance.Enums.TURQUOISE_COLOR);
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
    public void onRightClick() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT &&
                !AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead() &&
                !AbstractDungeon.actionManager.turnHasEnded)
            if (counter > 0) {
                atb(new DrawCardAction(DRAW_AMOUNT));
                atb(new DiscardAction(adp(), adp(), DISCARD_AMOUNT, false));
                counter--;
            }
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + CHARGE_COUNT + DESCRIPTIONS[1] + SHUFFLE_CHARGE + DESCRIPTIONS[2];
    }

    public void atBattleStart() {
        counter = CHARGE_COUNT;
    }

    public void onShuffle() {
        counter+= SHUFFLE_CHARGE;
    }

    public void onVictory() {
        counter = -1;
    }

    public boolean CanSpawn() {return adp().hasRelic(RuneOfIce.ID);}
}