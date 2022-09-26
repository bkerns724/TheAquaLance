package theExile.powers;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class ManaBurnPower extends AbstractExilePower {
    public static String POWER_ID = makeID(ManaBurnPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ManaBurnPower(int amount) {
        super(POWER_ID, PowerType.BUFF, true, adp(), amount);
        this.name = NAME;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        atb(new LoseHPAction(adp(), adp(), 1));
    }

    @Override
    public void atEndOfRound() {
        atb(new ReducePowerAction(owner, owner, this, 1));
    }
}