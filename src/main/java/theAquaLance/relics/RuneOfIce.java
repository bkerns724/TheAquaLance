package theAquaLance.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theAquaLance.TheAquaLance;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class RuneOfIce extends AbstractEasyRelic implements ClickableRelic {
    public static final String ID = makeID("RuneOfIce");
    private static final int CHARGE_COUNT = 1;
    private static final int DISCARD_AMOUNT = 1;
    private static final int SHUFFLE_CHARGE = 1;

    public RuneOfIce() {
        super(ID, RelicTier.STARTER, LandingSound.CLINK, TheAquaLance.Enums.TURQUOISE_COLOR);
    }

    public void onRightClick() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT &&
                !AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead() &&
                !AbstractDungeon.actionManager.turnHasEnded)
            if (counter > 0) {
                atb(new DiscardAction(adp(), adp(), DISCARD_AMOUNT, false));
                counter--;
            }
    }

    @Override
    public void atBattleStart() {
        counter = CHARGE_COUNT;
    }

    public void onShuffle() {
        counter+= SHUFFLE_CHARGE;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + CHARGE_COUNT + DESCRIPTIONS[1] + SHUFFLE_CHARGE + DESCRIPTIONS[2];
    }

    @Override
    public void onVictory() {
        counter = -1;
    }
}
