package theAquaLance.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import theAquaLance.TheAquaLance;
import theAquaLance.powers.OverExtendPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class SteamOrb extends AbstractEasyRelic {
    public static final String ID = makeID("SteamOrb");
    private static final int CARD_LIMIT = 6;
    private static final int OVEREXTEND = 2;

    public SteamOrb() {
        super(ID, RelicTier.BOSS, LandingSound.SOLID, TheAquaLance.Enums.AQUALANCE_TURQUOISE_COLOR);
    }

    public void onEquip() {
        ++adp().energy.energyMaster;
    }

    public void onUnequip() {
        --adp().energy.energyMaster;
    }

    @Override
    public void atTurnStart() {
        counter = 0;
        pulse = false;
    }

    @Override
    public void onVictory() {
        counter = -1;
        pulse = false;
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        counter++;
        if (counter == 6) {
            pulse = true;
            beginPulse();
        }
        if (counter > 6)
            applyToSelf(new OverExtendPower(adp(), OVEREXTEND));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + CARD_LIMIT + DESCRIPTIONS[1] + OVEREXTEND + DESCRIPTIONS[2];
    }
}