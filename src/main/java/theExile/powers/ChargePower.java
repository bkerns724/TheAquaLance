package theExile.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.cards.AbstractExileCard;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.LIGHTNING;
import static theExile.util.Wiz.adp;

public class ChargePower extends AbstractExilePower {
    public static String POWER_ID = makeID(ChargePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ChargePower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        canGoNegative = false;
        this.name = NAME;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractExileCard && ((AbstractExileCard) card).damageModList.contains(LIGHTNING))
            flash();
    }
}