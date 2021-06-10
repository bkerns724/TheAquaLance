package theAquaLance.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theAquaLance.AquaLanceMod;
import theAquaLance.patches.AbstractCardPatch;

import static theAquaLance.util.Wiz.*;

public class HedgePower extends AbstractEasyPower {
    public static final String POWER_ID = AquaLanceMod.makeID("Hedge");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HedgePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer && adp().hand.group.stream().anyMatch(c -> AbstractCardPatch.AbstractCardField.sigil.get(c)))
            applyToSelf(new DrawNextTurnPower(adp(), amount));
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        else
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}