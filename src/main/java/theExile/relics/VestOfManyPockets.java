package theExile.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theExile.TheExile;
import theExile.powers.AnticipatePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class VestOfManyPockets extends AbstractExileRelic {
    public static final String ID = makeID(VestOfManyPockets.class.getSimpleName());
    private static final int CYCLE_AMOUNT = 1;

    public VestOfManyPockets() {
        super(ID, AbstractRelic.RelicTier.COMMON, AbstractRelic.LandingSound.FLAT, TheExile.Enums.EXILE_BROWN_COLOR);
        amount = CYCLE_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void atBattleStart() {
        flash();
        applyToSelfTop(new AnticipatePower(CYCLE_AMOUNT));
        att(new RelicAboveCreatureAction(adp(), this));
    }
}
