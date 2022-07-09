package theArcanist.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.atb;

public class MarkOfTheVoid extends AbstractArcanistRelic {
    public static final String ID = makeID(MarkOfTheVoid.class.getSimpleName());
    public static final int TURNS_TO_LOSE_ENERGY = 3;

    public MarkOfTheVoid() {
        super(ID, RelicTier.SPECIAL, LandingSound.MAGICAL, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = TURNS_TO_LOSE_ENERGY;
        setUpdatedDescription();
    }
    
    public void onEquip() {
        counter = 0;
    }

    public void atTurnStart() {
        if (counter < 0)
            counter = 0;

        ++counter;

        if (counter == TURNS_TO_LOSE_ENERGY) {
            counter = 0;
            flash();
            atb(new RelicAboveCreatureAction(adp(), this));
            atb(new LoseEnergyAction(1));
        }

    }
}
