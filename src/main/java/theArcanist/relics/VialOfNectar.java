package theArcanist.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import theArcanist.TheArcanist;
import theArcanist.actions.MyAddTempHPAction;

import static theArcanist.ArcanistMod.makeID;

import static theArcanist.util.Wiz.*;

public class VialOfNectar extends AbstractArcanistRelic {
    public static final String ID = makeID(VialOfNectar.class.getSimpleName());
    public static final int TEMP_HP_AMOUNT = 3;

    public VialOfNectar() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = TEMP_HP_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void atBattleStart() {
        atb(new RelicAboveCreatureAction(adp(), this));
        atb(new MyAddTempHPAction(adp(), adp(), TEMP_HP_AMOUNT));
    }
}
