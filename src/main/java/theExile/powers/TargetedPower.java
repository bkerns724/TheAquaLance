package theExile.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.WeakPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class TargetedPower extends AbstractExilePower {
    public static String POWER_ID = makeID(TargetedPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TargetedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, true, owner, amount);
        this.name = NAME;
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        if (damageType == DamageInfo.DamageType.NORMAL && owner.hasPower(WeakPower.POWER_ID))
            return damage * 1.5f;
        return damage;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (amount == 1)
            atb(new RemoveSpecificPowerAction(owner, owner, this));
        else
            atb(new ReducePowerAction(owner, owner, POWER_ID, 1));
    }
}