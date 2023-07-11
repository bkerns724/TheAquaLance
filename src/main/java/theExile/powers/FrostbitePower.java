package theExile.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.SlowPower;
import theExile.ExileMod;

import static theExile.util.Wiz.applyToEnemyTop;

public class FrostbitePower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(FrostbitePower.class.getSimpleName());
    public final static float THRESHOLD = 0.2f;

    public FrostbitePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        priority = 99;
    }

    @Override
    public void onInitialApplication() {
        if (amount >= owner.currentHealth * THRESHOLD && !owner.hasPower(SlowPower.POWER_ID))
            applyToEnemyTop(owner, new SlowPower(owner, 0));
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (amount >= owner.currentHealth * THRESHOLD && !owner.hasPower(SlowPower.POWER_ID))
            applyToEnemyTop(owner, new SlowPower(owner, 0));
    }

    @Override
    public int onLoseHp(int damageAmount) {
        if (amount >= owner.currentHealth * THRESHOLD && !owner.hasPower(SlowPower.POWER_ID))
            applyToEnemyTop(owner, new SlowPower(owner, 0));

        return damageAmount;
    }
}