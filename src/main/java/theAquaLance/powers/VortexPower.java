package theAquaLance.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theAquaLance.AquaLanceMod;
import theAquaLance.cards.FormHelper;

import static theAquaLance.util.Wiz.*;

public class VortexPower extends AbstractEasyPower {
    public static String POWER_ID = AquaLanceMod.makeID("Vortex");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    FormHelper helperCard = new FormHelper();

    public VortexPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
        helperCard.baseSecondDamage = amount;
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        helperCard.baseSecondDamage = amount;
    }

    @Override
    public void onManualDiscard() {
        helperCard.applyPowers();
        flash();
        helperCard.allDmgTwo(AquaLanceMod.Enums.WATER);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}