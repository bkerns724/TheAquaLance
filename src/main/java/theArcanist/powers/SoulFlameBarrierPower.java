package theArcanist.powers;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;
import theArcanist.damagemods.SoulFireDamage;

import java.util.Arrays;

import static theArcanist.util.Wiz.*;

public class SoulFlameBarrierPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(SoulFlameBarrierPower.class.getSimpleName());
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
            DamageInfo thornInfo = new DamageInfo(adp(), amount, DamageInfo.DamageType.THORNS);
            DamageModifierManager.bindDamageMods(info, Arrays.asList(new SoulFireDamage()));
            DamageAction action = new DamageAction(info.owner, thornInfo, ArcanistMod.Enums.SOUL_FIRE);
            att(action);
        }
        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        removePower(this);
    }
}