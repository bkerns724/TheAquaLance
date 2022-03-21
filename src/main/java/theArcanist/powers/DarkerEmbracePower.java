package theArcanist.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.ArcanistMod;
import theArcanist.actions.MyAddTempHPAction;

import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.att;

public class DarkerEmbracePower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(DarkerEmbracePower.class.getSimpleName());

    public DarkerEmbracePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        att(new MyAddTempHPAction(adp(), adp(), amount));
        return damageAmount;
    }
}