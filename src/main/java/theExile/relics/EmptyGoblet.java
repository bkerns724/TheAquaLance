package theExile.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theExile.TheExile;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adRoom;
import static theExile.util.Wiz.adp;

public class EmptyGoblet extends AbstractExileRelic {
    public static final String ID = makeID(EmptyGoblet.class.getSimpleName());
    private static final int BUFF_GAIN = 1;

    public EmptyGoblet() {
        super(ID, RelicTier.SPECIAL, LandingSound.MAGICAL, TheExile.Enums.EXILE_BROWN_COLOR);
        amount = BUFF_GAIN;
        setUpdatedDescription();
    }

    @Override
    public void onPlayerEndTurn() {
        if (adp().hand.isEmpty()) {
            flash();
            Wiz.applyToSelfTop(new StrengthPower(adp(), amount));
            Wiz.applyToSelfTop(new DexterityPower(adp(), amount));
        }
        stopPulse();
    }

    @Override
    public void onRefreshHand() {
        if (AbstractDungeon.actionManager.actions.isEmpty() && adp().hand.isEmpty() && !AbstractDungeon.actionManager.turnHasEnded
                && !AbstractDungeon.isScreenUp && adRoom().phase == AbstractRoom.RoomPhase.COMBAT)
            beginLongPulse();
        else
            stopPulse();
    }
}
