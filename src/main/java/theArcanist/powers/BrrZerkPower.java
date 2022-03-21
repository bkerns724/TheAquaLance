package theArcanist.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.ArcanistMod;

public class BrrZerkPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(BrrZerkPower.class.getSimpleName());

    public BrrZerkPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        return super.onAttacked(info, damageAmount);
    }
}