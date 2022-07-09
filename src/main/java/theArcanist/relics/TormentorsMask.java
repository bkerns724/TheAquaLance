package theArcanist.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import theArcanist.TheArcanist;
import theArcanist.powers.JinxPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class TormentorsMask extends AbstractArcanistRelic {
    public static final String ID = makeID(TormentorsMask.class.getSimpleName());
    private static final int JINX_AMOUNT = 1;

    public TormentorsMask() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
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
