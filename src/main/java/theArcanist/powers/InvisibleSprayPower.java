package theArcanist.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.*;

public class InvisibleSprayPower extends AbstractEasyPower implements InvisiblePower {
    public static String POWER_ID = ArcanistMod.makeID("InvisibleSpray");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public InvisibleSprayPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void onManualDiscard() {
        atb(new GainBlockAction(adp(), amount, true));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}