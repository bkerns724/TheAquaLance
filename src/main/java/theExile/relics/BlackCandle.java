package theExile.relics;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import theExile.TheExile;
import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class BlackCandle extends AbstractExileRelic {
    public static final String ID = makeID(BlackCandle.class.getSimpleName());
    private static final int jinxAmount = 1;
    private static final int healthLoss = 1;

    public BlackCandle() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT, TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = jinxAmount;
        amount2 = healthLoss;
        setUpdatedDescription();
    }

    @Override
    public void onPlayerEndTurn() {
        flash();
        forAllMonstersLiving(m -> {
            atb(new RelicAboveCreatureAction(m, this));
            applyToEnemy(m, new JinxPower(m, amount));
        });
        atb(new LoseHPAction(adp(), adp(), healthLoss));
    }
}
