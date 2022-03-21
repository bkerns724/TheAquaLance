package theArcanist.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;


import static theArcanist.util.Wiz.*;

public class FearPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(FearPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static int counter = 0;

    public FearPower(AbstractCreature owner) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, -1);
        this.name = NAME;
        ID = POWER_ID + counter;
        counter++;
    }
}