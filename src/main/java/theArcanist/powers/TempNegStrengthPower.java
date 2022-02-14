package theArcanist.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static theArcanist.util.Wiz.*;

public class TempNegStrengthPower extends AbstractArcanistPower {
    public static final String POWER_ID = ArcanistMod.makeID("TempNegStrength");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TempNegStrengthPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        float output = damage - (float)amount;
        if (output < 0)
            output = 0;
        return type == DamageInfo.DamageType.NORMAL ? output : damage;
    }

    @Override
    public void atEndOfRound() {
        atb(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}