package theAquaLance.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import theAquaLance.AquaLanceMod;
import theAquaLance.relics.Chains;

import static theAquaLance.util.Wiz.*;

public class SoakedPower extends AbstractEasyPower {
    public static final String POWER_ID = AquaLanceMod.makeID("Soaked");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean justApplied = false;

    public SoakedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
    }

    public SoakedPower(AbstractCreature owner, int amount, boolean isSourceMonster) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
        if (isSourceMonster)
            this.justApplied = true;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (justApplied) {
            justApplied = false;
            return;
        }
        if (amount <= 1 && !adp().hasRelic(Chains.ID))
            atb(new RemoveSpecificPowerAction(owner, owner, this));
        else if (!adp().hasRelic(Chains.ID)){
            atb(new ReducePowerAction(owner, owner, POWER_ID, 1));
        }
    }

    @Override
    public void updateDescription() {
        if (!adp().hasRelic(Chains.ID))
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + 1 + DESCRIPTIONS[2];
        else
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[3];
    }

    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (type == DamageInfo.DamageType.NORMAL)
            return damage + amount;
        else
            return damage;
    }
}