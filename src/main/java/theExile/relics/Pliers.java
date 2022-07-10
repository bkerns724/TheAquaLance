package theExile.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theExile.TheExile;
import theExile.powers.CrushedPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class Pliers extends AbstractExileRelic implements OnApplyPowerRelic {
    public static final String ID = makeID("Pliers");
    public static final int VIGOR_AMOUNT = 3;

    public Pliers() {
        super(ID, RelicTier.UNCOMMON,
                LandingSound.CLINK,
                TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = VIGOR_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public boolean onApplyPower(AbstractPower pow, AbstractCreature target, AbstractCreature source) {
        if (pow instanceof CrushedPower && source == adp()) {
            flash();
            atb(new RelicAboveCreatureAction(adp(), this));
            applyToSelf(new VigorPower(adp(), amount));
        }
        return true;
    }
}
