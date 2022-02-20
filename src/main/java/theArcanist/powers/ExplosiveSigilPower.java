package theArcanist.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.*;

public class ExplosiveSigilPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID("ExplosiveSigil");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ExplosiveSigilPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void onDiscardSigil() {
        forAllMonstersLiving(m -> att(new LoseHPAction(m, owner, amount, AbstractGameAction.AttackEffect.FIRE)));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}