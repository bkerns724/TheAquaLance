package theExile.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;

import static theExile.util.Wiz.applyToEnemy;

public class BrrZerkPower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(BrrZerkPower.class.getSimpleName());

    public BrrZerkPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != owner && info.type == DamageInfo.DamageType.NORMAL) {
            flash();
            applyToEnemy(target, new FrostbitePower(target, amount));
        }
    }
}