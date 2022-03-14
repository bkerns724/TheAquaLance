package theArcanist.relics;

import com.megacrit.cardcrawl.powers.DexterityPower;
import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class CeremonialRobe extends AbstractArcanistRelic {
    public static final String ID = makeID("CeremonialRobe");
    private static final int DEX_LOSS = 1;
    private static final int DRAW_AMOUNT = 1;

    public CeremonialRobe() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
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
        applyToSelf(new DexterityPower(adp(), -DEX_LOSS));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DRAW_AMOUNT + DESCRIPTIONS[1] + DEX_LOSS + DESCRIPTIONS[2];
    }
}