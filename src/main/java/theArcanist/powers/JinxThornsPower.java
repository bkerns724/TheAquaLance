package theArcanist.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.*;

public class JinxThornsPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID("JinxThorns");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public JinxThornsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        flash();
        int damAmount = amount*getJinxAmount(info.owner);
        if (damAmount > 0)
            thornDmg(info.owner, damAmount);
        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        removePower(this);
    }
}