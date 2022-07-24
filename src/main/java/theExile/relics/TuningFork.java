package theExile.relics;

import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import theExile.ExileMod;
import theExile.TheExile;
import theExile.cards.AbstractResonantCard;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class TuningFork extends AbstractExileRelic {
    public static final String ID = makeID(TuningFork.class.getSimpleName());
    public static final int DAMAGE_AMOUNT = 3;

    public TuningFork() {
        super(ID, RelicTier.UNCOMMON, LandingSound.MAGICAL, TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = DAMAGE_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard instanceof AbstractResonantCard)
            atb(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(DAMAGE_AMOUNT,
                    true), DamageInfo.DamageType.THORNS, ExileMod.Enums.RESONANT));
    }
}
