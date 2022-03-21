package theArcanist.powers;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;
import theArcanist.Orbs.CrazyPanda;
import theArcanist.cards.SummonMonster;

import static theArcanist.util.Wiz.*;

public class SummonMonsterPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(SummonMonsterPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SummonMonsterPower(AbstractCreature owner, int amount, int amount2) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.amount2 = amount2;
        ID = POWER_ID + amount2;
        isTwoAmount = true;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        for (int i = 0; i < amount; i++) {
            atb(new IncreaseMaxOrbAction(1));
            atb(new ChannelAction(new CrazyPanda(amount2)));
        }
    }
}