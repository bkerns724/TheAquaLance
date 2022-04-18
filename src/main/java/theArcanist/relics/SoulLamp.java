package theArcanist.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import theArcanist.TheArcanist;
import theArcanist.powers.SoulFlameBarrierPower;

import static theArcanist.ArcanistMod.makeID;

import static theArcanist.util.Wiz.*;

public class SoulLamp extends AbstractArcanistRelic {
    public static final String ID = makeID(SoulLamp.class.getSimpleName());
    public static final int BLOCK_THRESHOLD = 6;

    public SoulLamp() {
        super(ID, RelicTier.SPECIAL, LandingSound.CLINK, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = BLOCK_THRESHOLD;
        setUpdatedDescription();
    }

    @Override
    public void onPlayerEndTurn() {
        int flameAmount = adp().currentBlock/BLOCK_THRESHOLD;
        if (flameAmount > 0) {
            flash();
            applyToSelfTop(new SoulFlameBarrierPower(adp(), flameAmount));
            att(new RelicAboveCreatureAction(adp(), this));
        }
    }
}
