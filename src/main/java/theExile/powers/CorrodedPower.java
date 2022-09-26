package theExile.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class CorrodedPower extends AbstractExilePower {
    public static String POWER_ID = makeID(CorrodedPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CorrodedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        return damageType == DamageInfo.DamageType.NORMAL ? damage + amount : damage;
    }

    @Override
    public void atEndOfRound() {
        atb(new RemoveSpecificPowerAction(owner, owner, this));
    }
}