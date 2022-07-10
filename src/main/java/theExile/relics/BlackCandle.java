package theExile.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import theExile.TheExile;
import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class BlackCandle extends AbstractExileRelic {
    public static final String ID = makeID(BlackCandle.class.getSimpleName());
    private static final int jinxAmount = 4;

    public BlackCandle() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT, TheExile.Enums.EXILE_BLARPLE_COLOR);
        cardToPreview = new VoidCard();
        amount = jinxAmount;
        setUpdatedDescription();
    }

    @Override
    public void onPlayerEndTurn() {
        flash();
        forAllMonstersLiving(m -> {
            atb(new RelicAboveCreatureAction(m, this));
            applyToEnemy(m, new JinxPower(m, amount));
        });
        atb(new MakeTempCardInDiscardAction(new VoidCard(), 1));
    }
}
