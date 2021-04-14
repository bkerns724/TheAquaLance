package theAquaLance.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theAquaLance.TheAquaLance;
import theAquaLance.patches.AbstractCardPatch.AbstractCardField;
import theAquaLance.powers.AquaDrawNextTurnPower;

import static theAquaLance.AquaLanceMod.makeID;

import static theAquaLance.util.Wiz.*;

public class SailorCharm extends AbstractEasyRelic {
    public static final String ID = makeID("SailorCharm");
    private static final int DRAW_AMOUNT = 1;

    public SailorCharm() {
        super(ID, RelicTier.UNCOMMON, LandingSound.MAGICAL, TheAquaLance.Enums.TURQUOISE_COLOR);
    }

    public void onPlayerEndTurn() {
        boolean hasSigil = false;
        for (AbstractCard c : adp().hand.group) {
            if (AbstractCardField.sigil.get(c))
                hasSigil = true;
        }
        if (hasSigil) {
            flash();
            atb(new ApplyPowerAction(adp(), adp(), new AquaDrawNextTurnPower(adp(), DRAW_AMOUNT), DRAW_AMOUNT));
        }
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}