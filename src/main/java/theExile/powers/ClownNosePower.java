package theExile.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.ExileMod;

public class ClownNosePower extends AbstractExilePower implements InvisiblePower {
    public static String POWER_ID = ExileMod.makeID(ClownNosePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ClownNosePower(AbstractCreature owner) {
        super(POWER_ID, PowerType.BUFF, false, owner,1);
        this.name = NAME;
    }


    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}