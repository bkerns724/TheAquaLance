package theExile.powers;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class HexingPower extends AbstractExilePower {
    // intellij stuff buff, false
    public static String POWER_ID = makeID(HexingPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HexingPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        forAllMonstersLiving(m -> atb(new LoseHPAction(m, adp(), getJinxAmount(m))));
    }
}