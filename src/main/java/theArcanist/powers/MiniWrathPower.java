package theArcanist.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;


import static theArcanist.util.Wiz.*;

public class MiniWrathPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(MiniWrathPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static final float DAMAGE_MULTIPLIER = 1.5f;

    public MiniWrathPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        atb(new ReducePowerAction(adp(), adp(), this, 1));
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return damage *= DAMAGE_MULTIPLIER;
    }
}