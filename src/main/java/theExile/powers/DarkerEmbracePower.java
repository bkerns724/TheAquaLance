package theExile.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;
import theExile.actions.MyAddTempHPAction;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.att;

public class DarkerEmbracePower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(DarkerEmbracePower.class.getSimpleName());

    public DarkerEmbracePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != owner && info.type == DamageInfo.DamageType.NORMAL)
            att(new MyAddTempHPAction(adp(), adp(), amount));
        return damageAmount;
    }
}