package theArcanist.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;
import theArcanist.actions.IgnorePainAction;

import static theArcanist.util.Wiz.*;

public class IgnorePainPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID("IgnorePain");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public IgnorePainPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void atStartOfTurn() {
        int count = amount;
        for (AbstractCard card : adp().drawPile.group) {
            if (card.type == AbstractCard.CardType.STATUS) {
                atb(new IgnorePainAction(card));
                --count;
            }
            if (count == 0)
                return;
        }
        for (AbstractCard card : adp().discardPile.group) {
            if (card.type == AbstractCard.CardType.STATUS) {
                atb(new IgnorePainAction(card));
                --count;
            }
            if (count == 0)
                return;
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}