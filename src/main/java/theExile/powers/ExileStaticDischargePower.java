package theExile.powers;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import theExile.damagemods.LightningDamage;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class ExileStaticDischargePower extends AbstractExilePower {
    public static String POWER_ID = makeID(ExileStaticDischargePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean ready;

    public ExileStaticDischargePower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
        ready = true;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (ready && info.owner != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            ready = false;
            DamageInfo newInfo = new DamageInfo(adp(), amount, DamageInfo.DamageType.THORNS);
            DamageModifierManager.bindDamageMods(info, new DamageModContainer(this, new LightningDamage()));
            att(new RemoveSpecificPowerAction(adp(), adp(), this));
            att(new DamageAction(info.owner, newInfo, AbstractGameAction.AttackEffect.NONE));
            att(new VFXAction(new LightningEffect(info.owner.drawX, info.owner.drawY)));
            att(new SFXAction("ORB_LIGHTNING_EVOKE"));
        }

        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        atb(new RemoveSpecificPowerAction(adp(), adp(), this));
    }
}