package theAquaLance.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theAquaLance.AquaLanceMod;
import theAquaLance.relics.PaperFish;

import static theAquaLance.util.Wiz.*;

public class SoakedPower extends AbstractEasyPower {
    public static final String POWER_ID = AquaLanceMod.makeID("Soaked");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean justApplied = false;
    public static final int NORMAL_SOAK = 50;

    public SoakedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, true, owner, amount);
        name = NAME;
        priority = 50;
    }

    public SoakedPower(AbstractCreature owner, int amount, boolean isSourceMonster) {
        super(POWER_ID, PowerType.DEBUFF, true, owner, amount);
        name = NAME;
        if (isSourceMonster)
            justApplied = true;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (justApplied) {
            justApplied = false;
            return;
        }
        if (amount <= 1)
            atb(new RemoveSpecificPowerAction(owner, owner, this));
        else
            atb(new ReducePowerAction(owner, owner, POWER_ID, 1));
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            if (adp().hasRelic(PaperFish.ID)) {
                description = DESCRIPTIONS[0] + PaperFish.SOAK_POWER + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
            } else {
                description = DESCRIPTIONS[0] + NORMAL_SOAK + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
            }
        } else if (adp().hasRelic(PaperFish.ID)) {
            description = DESCRIPTIONS[0] + PaperFish.SOAK_POWER + DESCRIPTIONS[1] + amount + DESCRIPTIONS[3];
        } else {
            description = DESCRIPTIONS[0] + NORMAL_SOAK + DESCRIPTIONS[1] + amount + DESCRIPTIONS[3];
        }
    }

    public float cardCalcHelper(float damage) {
        return adp().hasRelic(PaperFish.ID) ? damage * (100.0F + PaperFish.SOAK_POWER)/100.0F :
                damage * (100.0F + NORMAL_SOAK)/100.0F;
    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.NORMAL) {
            double foo = damageAmount * (adp().hasRelic(PaperFish.ID) ? damageAmount * (100.0F + PaperFish.SOAK_POWER)/100.0F:
                    damageAmount * (100.0F + NORMAL_SOAK)/100.0F);
            damageAmount = (int)Math.floor(foo);
        }
        return damageAmount;
    }
}