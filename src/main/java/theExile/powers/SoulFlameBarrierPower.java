package theExile.powers;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.ExileMod;
import theExile.damagemods.SoulFireDamage;

import java.util.Collections;

import static theExile.util.Wiz.att;
import static theExile.util.Wiz.removePower;

public class SoulFlameBarrierPower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(SoulFlameBarrierPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SoulFlameBarrierPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type == DamageInfo.DamageType.NORMAL && info.owner != this.owner) {
            flash();
            DamageInfo thornInfo = new DamageInfo(this.owner, amount, DamageInfo.DamageType.THORNS);
            DamageModifierManager.bindDamageMods(thornInfo, Collections.singletonList(new SoulFireDamage()));
            DamageAction action = new DamageAction(info.owner, thornInfo, ExileMod.Enums.SOUL_FIRE);
            att(action);
        }
        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        removePower(this);
    }
}