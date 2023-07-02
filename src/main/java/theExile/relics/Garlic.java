package theExile.relics;

import theExile.TheExile;
import theExile.powers.CorrodedPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.forAllMonstersLiving;

public class Garlic extends AbstractExileRelic {
    public static final String ID = makeID(Garlic.class.getSimpleName());
    public static final int CORROSION_AMOUNT = 3;

    public Garlic() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, TheExile.Enums.EXILE_BROWN_COLOR);
        amount = CORROSION_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void atBattleStart() {
        forAllMonstersLiving(m -> applyToEnemy(m, new CorrodedPower(m, CORROSION_AMOUNT)));
    }
}
