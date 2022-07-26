package theExile.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.FlightPower;

import static theExile.ExileMod.makeID;

public class CrumblingPower extends AbstractExilePower {
    public static String POWER_ID = makeID(CrumblingPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CrumblingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
        priority = 1;
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        // Kinda hacky
        if (!owner.hasPower(FlightPower.POWER_ID))
            return damageType == DamageInfo.DamageType.NORMAL ? damage + amount : damage;
        else
            return damageType == DamageInfo.DamageType.NORMAL ? damage + 2*amount : damage;
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        return info.type != DamageInfo.DamageType.NORMAL ? damageAmount + amount : damageAmount;
    }
}