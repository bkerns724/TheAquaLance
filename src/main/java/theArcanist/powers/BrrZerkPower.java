package theArcanist.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.applyToEnemy;

public class BrrZerkPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(BrrZerkPower.class.getSimpleName());

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