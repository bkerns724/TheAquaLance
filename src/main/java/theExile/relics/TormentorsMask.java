package theExile.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import theExile.TheExile;
import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class TormentorsMask extends AbstractExileRelic {
    public static final String ID = makeID(TormentorsMask.class.getSimpleName());
    private static final int JINX_AMOUNT = 1;

    public TormentorsMask() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, TheExile.Enums.EXILE_BROWN_COLOR);
        amount = JINX_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void atBattleStart() {
        forAllMonstersLiving(m -> {
            atb(new RelicAboveCreatureAction(adp(), this));
            applyToEnemy(m, new JinxPower(m, JINX_AMOUNT));
        });
    }
}
