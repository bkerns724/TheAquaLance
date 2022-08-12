package theExile.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class SpiritPressurePower extends AbstractExilePower {
    public static String POWER_ID = makeID(SpiritPressurePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SpiritPressurePower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (target != source && power instanceof JinxPower)
            power.amount += amount;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        forAllMonstersLiving(m -> applyToEnemy(m, new JinxPower(m, amount)));
    }
}