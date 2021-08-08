package theAquaLance.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theAquaLance.AquaLanceMod;

import static theAquaLance.util.Wiz.*;

public class IntelligencePower extends AbstractEasyPower {
    public static String POWER_ID = AquaLanceMod.makeID("Int");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public IntelligencePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    public void stackPower(int stackAmount) {
        fontScale = 8.0F;
        amount += stackAmount;
        if (amount == 0) {
            addToTop(new RemoveSpecificPowerAction(owner, owner, this));
        }

        if (amount >= 999) {
            amount = 999;
        }

        if (amount <= -999) {
            amount = -999;
        }
    }

    public void reducePower(int reduceAmount) {
        fontScale = 8.0F;
        amount -= reduceAmount;
        if (amount == 0) {
            addToTop(new RemoveSpecificPowerAction(owner, owner, this));
        }

        if (amount >= 999) {
            amount = 999;
        }

        if (amount <= -999) {
            amount = -999;
        }

    }

    public void updateDescription() {
        if (amount > 0) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
            type = PowerType.BUFF;
        } else {
            int tmp = -amount;
            description = DESCRIPTIONS[1] + tmp + DESCRIPTIONS[2];
            type = PowerType.DEBUFF;
        }
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == AquaLanceMod.Enums.MAGIC ? damage + (float)amount : damage;
    }
}