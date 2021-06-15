package theAquaLance.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theAquaLance.AquaLanceMod;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static theAquaLance.util.Wiz.*;

public class WideOpenPower extends AbstractEasyPower {
    public static String POWER_ID = AquaLanceMod.makeID("WideOpen");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public WideOpenPower(AbstractCreature owner, int amount) {
        super("Boop", PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        damage *= amount;
        att(new RemoveSpecificPowerAction(owner, owner, this));
        return  damage;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}