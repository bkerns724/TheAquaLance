package theExile.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.cards.AbstractExileCard;
import theExile.cards.cardUtil.Resonance;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

//Code in ResonantPowerPatch
public class ConvertPower extends AbstractExilePower {
    public static String POWER_ID = makeID(ConvertPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ConvertPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!(card instanceof AbstractExileCard))
            return;
        Resonance resonance = new Resonance();
        resonance.cards.add((AbstractExileCard) card.makeStatEquivalentCopy());
        resonance.toPower();
        atb(new ReducePowerAction(owner, owner, this, 1));
    }
}