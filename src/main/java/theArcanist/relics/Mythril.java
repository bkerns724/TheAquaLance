package theArcanist.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Mythril extends AbstractArcanistRelic {
    public static final String ID = makeID(Mythril.class.getSimpleName());
    private static final int BLOCK_AMT = 6;

    public Mythril() {
        super(ID, RelicTier.COMMON, LandingSound.HEAVY, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = BLOCK_AMT;
        setUpdatedDescription();
    }

    @Override
    public void onPlayerEndTurn() {
        if (adp().hand.isEmpty()) {
            flash();
            att(new GainBlockAction(adp(), BLOCK_AMT));
            att(new RelicAboveCreatureAction(adp(), this));
        }
        stopPulse();
    }

    @Override
    public void onVictory() {
        stopPulse();
    }

    @Override
    public void onRefreshHand() {
        if (AbstractDungeon.actionManager.actions.isEmpty() && adp().hand.isEmpty() && !AbstractDungeon.actionManager.turnHasEnded
                && !AbstractDungeon.isScreenUp && adRoom().phase == AbstractRoom.RoomPhase.COMBAT)
            beginLongPulse();
    }
}
