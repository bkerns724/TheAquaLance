package theAquaLance.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import theAquaLance.AquaLanceMod;
import theAquaLance.relics.RuneOfIce;

import static theAquaLance.util.Wiz.*;

public class HobbledPower extends AbstractEasyPower implements BetterOnApplyPowerPower {
    public static final String POWER_ID = AquaLanceMod.makeID("Hobbled");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESC = powerStrings.DESCRIPTIONS;

    public HobbledPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public boolean betterOnApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.type == PowerType.DEBUFF && target != source
                && !(power instanceof GainStrengthPower)) {
            flash();
            if (amount <= 0)  // This shouldn't happen? But ArtifactPower code had it.  No harm done including it
                addToTop(new RemoveSpecificPowerAction(owner, owner, this));
            else
                addToTop(new ReducePowerAction(owner, owner, this, 1));
            return false;
        }
        return true;
    }

    @Override
    public void updateDescription() {
        if (amount != 1)
            description = DESC[0] + amount + DESC[1];
        else
            description = DESC[0] + amount + DESC[2];

    }
}
