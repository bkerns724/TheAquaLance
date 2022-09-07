package theExile.relics;

import com.evacipated.cardcrawl.mod.stslib.damagemods.BindingHelper;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import theExile.TheExile;
import theExile.damagemods.EldritchDamage;

import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType.THORNS;
import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class VoidBracelet extends AbstractExileRelic {
    public static final String ID = makeID(VoidBracelet.class.getSimpleName());
    private static final int ELDRITCH_THORNS = 3;

    public VoidBracelet() {
        super(ID, RelicTier.SPECIAL, LandingSound.MAGICAL, TheExile.Enums.EXILE_BROWN_COLOR);
        amount = ELDRITCH_THORNS;
        setUpdatedDescription();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner != adp()) {
            flash();
            atb(new RelicAboveCreatureAction(adp(), this));
            DamageModContainer container = new DamageModContainer(this, new EldritchDamage());
            DamageInfo revenge = BindingHelper.makeInfo(container, adp(), amount, THORNS);
            att(new DamageAction(info.owner, revenge));
        }

        return damageAmount;
    }
}
