package theAquaLance.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theAquaLance.AquaLanceMod;

public class IceMasteryPower extends AbstractEasyPower {
    public static String POWER_ID = AquaLanceMod.makeID("IceMastery");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public IceMasteryPower(AbstractCreature owner, int amount) {
        super("IceMastery", PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }


    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + (amount + 1) + DESCRIPTIONS[1];
    }
}