package theExile.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import theExile.TheExile;
import theExile.actions.MyAddTempHPAction;

import static theExile.ExileMod.makeID;

import static theExile.util.Wiz.*;

public class VialOfNectar extends AbstractExileRelic {
    public static final String ID = makeID(VialOfNectar.class.getSimpleName());
    public static final int TEMP_HP_AMOUNT = 3;

    public VialOfNectar() {
        super(ID, RelicTier.UNCOMMON, LandingSound.CLINK, TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = TEMP_HP_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void atBattleStart() {
        atb(new RelicAboveCreatureAction(adp(), this));
        atb(new MyAddTempHPAction(adp(), adp(), TEMP_HP_AMOUNT));
    }
}
