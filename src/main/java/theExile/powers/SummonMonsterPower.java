package theExile.powers;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.ExileMod;
import theExile.orbs.CrazyPanda;

import static theExile.util.Wiz.atb;

public class SummonMonsterPower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(SummonMonsterPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static int idOffset = 0;

    public SummonMonsterPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        ID = POWER_ID + idOffset;
        idOffset++;
    }

    @Override
    public void atStartOfTurn() {
        atb(new ChannelAction(new CrazyPanda(amount)));
    }
}