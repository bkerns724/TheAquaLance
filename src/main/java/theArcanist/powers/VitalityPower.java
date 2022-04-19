package theArcanist.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.RitualDagger;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;

public class VitalityPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(VitalityPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public VitalityPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    public float modifyBlock(float blockAmount, AbstractCard card) {
        if (card.baseBlock > 0)
            return blockAmount + amount;
        else
            return blockAmount;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.baseBlock > 0 && !card.cardID.equals(RitualDagger.ID)) {
            flash();
            addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
        }
    }
}