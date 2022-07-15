package theExile.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theExile.ExileMod;
import theExile.relics.UnmeltingIce;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.att;

public class FrostbitePower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(FrostbitePower.class.getSimpleName());

    public FrostbitePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        if ( adp().hasRelic(UnmeltingIce.ID) )
            this.amount += UnmeltingIce.FROST_BOOST;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner == owner) {
            flash();
            //AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, ExileMod.Enums.ICE));
            if (owner.hasPower(WaterfallPower.POWER_ID)) {
                att(new DamageAction(owner, new DamageInfo(owner, amount * (1 + owner.getPower(WaterfallPower.POWER_ID).amount),
                                DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE, true));
            } else {
                att(new DamageAction(owner, new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS),
                        AbstractGameAction.AttackEffect.NONE, true));
            }
            if (amount > 1)
                reducePower(1);
            else
                att(new RemoveSpecificPowerAction(owner, owner, this));
            updateDescription();
            AbstractDungeon.onModifyPower();
        }
    }
}