package theArcanist.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theArcanist.ArcanistMod;
import theArcanist.relics.UnmeltingIce;

import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.att;

public class FrostbitePower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(FrostbitePower.class.getSimpleName());

    public FrostbitePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        if ( adp().hasRelic(UnmeltingIce.ID) )
            this.amount += UnmeltingIce.FROST_BOOST;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner == owner) {
            flash();
            if (owner.hasPower(WaterfallPower.POWER_ID))
                att(new DamageAction(owner,
                        new DamageInfo(owner, amount*(1 + owner.getPower(WaterfallPower.POWER_ID).amount),
                                DamageInfo.DamageType.THORNS),
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
            else
                att(new DamageAction(owner,
                        new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS),
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
            if (amount > 1)
                reducePower(1);
            else
                att(new RemoveSpecificPowerAction(owner, owner, this));
            updateDescription();
            AbstractDungeon.onModifyPower();
        }
    }
}