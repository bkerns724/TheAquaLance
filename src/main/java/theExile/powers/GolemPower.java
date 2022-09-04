package theExile.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class GolemPower extends AbstractExilePower {
    public static String POWER_ID = makeID(GolemPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean ready;

    public GolemPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
        ready = true;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        ready = true;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (!ready)
            return damageAmount;
        if (info.owner != null && info.type == DamageInfo.DamageType.NORMAL && info.owner != owner) {
            DamageInfo info2 = new DamageInfo(adp(), amount, DamageInfo.DamageType.THORNS);
            if (amount < Wiz.DAMAGE_THRESHOLD_L)
                att(new DamageAction(info.owner, info2, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            else {
                AbstractCreature m = info.owner;
                att(new DamageAction(info.owner, info2, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                vfxTop(new VerticalImpactEffect(m.hb.cX + m.hb.width / 4.0F, m.hb.cY - m.hb.height / 4.0F));
            }
        }
        ready = false;
        return damageAmount;
    }
}