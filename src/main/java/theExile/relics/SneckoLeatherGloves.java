package theExile.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theExile.TheExile;
import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.att;

public class SneckoLeatherGloves extends AbstractExileRelic implements OnApplyPowerRelic {
    public static final String ID = makeID(SneckoLeatherGloves.class.getSimpleName());
    private static final int HP_LOSS = 5;

    public SneckoLeatherGloves() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, TheExile.Enums.EXILE_BROWN_COLOR);
        amount = HP_LOSS;
        setUpdatedDescription();
    }

    @Override
    public boolean onApplyPower(AbstractPower abstractPower, AbstractCreature target, AbstractCreature source) {
        if (abstractPower instanceof JinxPower) {
            att(new LoseHPAction(target, source, amount));
            att(new RelicAboveCreatureAction(target, this));
        }
        return true;
    }
}
