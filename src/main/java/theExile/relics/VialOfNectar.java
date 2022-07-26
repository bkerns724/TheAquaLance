package theExile.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import theExile.TheExile;
import theExile.actions.MyAddTempHPAction;

import static theExile.ExileMod.makeID;

import static theExile.util.Wiz.*;

public class VialOfNectar extends AbstractExileRelic {
    public static final String ID = makeID(VialOfNectar.class.getSimpleName());
    public static final int TEMP_HP_AMOUNT = 2;

    public VialOfNectar() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK, TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = TEMP_HP_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void atBattleStart() {
        att(new MyAddTempHPAction(adp(), adp(), TEMP_HP_AMOUNT));
        att(new RelicAboveCreatureAction(adp(), this));
    }
}
