package theExile.relics;

import theExile.TheExile;
import theExile.powers.JinxPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class BlackKatCharm extends AbstractExileRelic {
    public static final String ID = makeID(BlackKatCharm.class.getSimpleName());
    private static final int JINX_AMOUNT = 1;

    public BlackKatCharm() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheExile.Enums.EXILE_BROWN_COLOR);
        amount = JINX_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void onManualDiscard() {
        Wiz.forAllMonstersLiving(m -> Wiz.applyToEnemy(m, new JinxPower(m, JINX_AMOUNT)));
    }
}
