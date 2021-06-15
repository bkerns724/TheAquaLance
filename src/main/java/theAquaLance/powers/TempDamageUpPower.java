package theAquaLance.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theAquaLance.AquaLanceMod;

import static theAquaLance.util.Wiz.*;

public class TempDamageUpPower extends AbstractEasyPower {
    public static String POWER_ID = AquaLanceMod.makeID("TempDamageUp");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TempDamageUpPower(AbstractCreature owner, int amount) {
        super("TempDamageUp", PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return damage += amount;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}