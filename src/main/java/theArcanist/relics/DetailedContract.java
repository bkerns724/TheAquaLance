package theArcanist.relics;

import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;

import static theArcanist.util.Wiz.*;

public class DetailedContract extends AbstractArcanistRelic {
    public static final String ID = makeID(DetailedContract.class.getSimpleName());
    public static final int HP_LOSS = 5;

    public DetailedContract() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = HP_LOSS;
        setUpdatedDescription();
    }

    @Override
    public void onEquip() {
        adp().decreaseMaxHealth(HP_LOSS);
    }
}
