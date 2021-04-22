package theAquaLance.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theAquaLance.AquaLanceMod;
import theAquaLance.relics.Chains;

import static theAquaLance.util.Wiz.*;

public class DrowningPower extends AbstractEasyPower {
    public static String POWER_ID = AquaLanceMod.makeID("Drowned");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public final static int DECAY = 25;
    public final static int RELIC_DECAY = 15;

    public DrowningPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, true, owner, amount);
        this.name = NAME;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (amount <= 1)
            atb(new RemoveSpecificPowerAction(owner, owner, this));
        else {
            int decay = (adp().hasRelic(Chains.ID)) ? RELIC_DECAY : DECAY;
            int reduce_amount = (int)Math.ceil(amount * decay/100.0);
            atb(new ReducePowerAction(owner, owner, POWER_ID, reduce_amount));
        }
    }

    @Override
    public void updateDescription() {
        int decay = (adp().hasRelic(Chains.ID)) ? RELIC_DECAY : DECAY;
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + decay + DESCRIPTIONS[2];
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        return damage + amount;
    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.NORMAL)
            return damageAmount + amount;
        return damageAmount;
    }
}