package theExile.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class HarmonyPower extends AbstractExilePower {
    public static String POWER_ID = makeID(HarmonyPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HarmonyPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (adp() == null)
            return;
        ResonatingPower pow = (ResonatingPower) adp().getPower(ResonatingPower.POWER_ID);
        if (pow != null && pow.amount > 0) {
            atb(new GainBlockAction(adp(), amount * pow.amount));
        }
    }
}