package theExile.relics;

import theExile.TheExile;

import static theExile.ExileMod.makeID;

//code in ExileMod
public class TwistedShuriken extends AbstractExileRelic {
    public static final String ID = makeID(TwistedShuriken.class.getSimpleName());
    private static final int ATTACKS_TO_TRIGGER = 3;
    public static final int CHARGE_AMOUNT = 2;

    public TwistedShuriken() {
        super(ID, RelicTier.UNCOMMON, LandingSound.CLINK, TheExile.Enums.EXILE_BROWN_COLOR);
        amount = ATTACKS_TO_TRIGGER;
        amount2 = CHARGE_AMOUNT;
        setUpdatedDescription();
    }

    public void atTurnStart() {
        counter = 0;
    }

    public void onVictory() {
        counter = -1;
    }
}
