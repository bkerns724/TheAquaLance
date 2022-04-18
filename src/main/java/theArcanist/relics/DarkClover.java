package theArcanist.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class DarkClover extends AbstractArcanistRelic {
    public static final String ID = makeID(DarkClover.class.getSimpleName());
    public static final int JINX_THORNS_AMOUNT = 1;

    public DarkClover() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = JINX_THORNS_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner == adp() || info.type != DamageInfo.DamageType.NORMAL)
            return damageAmount;
        AbstractCreature target = info.owner;

        int count = getJinxAmount(target);
        if (count > 0) {
            flash();
            att(new RelicAboveCreatureAction(target, this));
            thornDmgTop(target, count);
        }
        return damageAmount;
    }
}
