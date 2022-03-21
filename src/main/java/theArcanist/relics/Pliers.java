package theArcanist.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theArcanist.TheArcanist;
import theArcanist.powers.CrushedPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.applyToSelf;

public class Pliers extends AbstractArcanistRelic implements OnApplyPowerRelic {
    public static final String ID = makeID("Pliers");
    public static final int VIGOR_AMOUNT = 3;

    public Pliers() {
        super(ID, RelicTier.UNCOMMON,
                LandingSound.CLINK,
                TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = VIGOR_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public boolean onApplyPower(AbstractPower pow, AbstractCreature target, AbstractCreature source) {
        if (pow instanceof CrushedPower && source == adp())
            applyToSelf(new VigorPower(adp(), amount));
        return true;
    }
}
