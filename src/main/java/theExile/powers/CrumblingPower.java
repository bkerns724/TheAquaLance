package theExile.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theExile.ExileMod.makeID;

public class CrumblingPower extends AbstractExilePower {
    public static String POWER_ID = makeID(CrumblingPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CrumblingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        return damageType != DamageInfo.DamageType.NORMAL ? damage * (1f + amount/100f)  : damage;
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        return super.atDamageFinalReceive(damage, type, card);
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        return info.type != DamageInfo.DamageType.NORMAL ? (int)(damageAmount * (1f + amount/100f))  : damageAmount;
    }
}