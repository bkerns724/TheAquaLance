package theAquaLance.relics;

import com.megacrit.cardcrawl.powers.StrengthPower;
import theAquaLance.TheAquaLance;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class CeremonialRobe extends AbstractEasyRelic {
    public static final String ID = makeID("CeremonialRobe");
    private static final int STR_LOSS = 1;
    private static final int DRAW_AMOUNT = 1;

    public CeremonialRobe() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT, TheAquaLance.Enums.TURQUOISE_COLOR);
    }

    public void onEquip() {
        adp().masterHandSize += DRAW_AMOUNT;
    }

    public void onUnequip() {
        adp().masterHandSize -= DRAW_AMOUNT;
    }

    public void atTurnStart() {
        flash();
    }

    @Override
    public void atBattleStart() {
        applyToSelf(new StrengthPower(adp(), -STR_LOSS));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DRAW_AMOUNT + DESCRIPTIONS[1] + STR_LOSS + DESCRIPTIONS[2];
    }
}