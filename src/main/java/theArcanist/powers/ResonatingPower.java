package theArcanist.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.ArcanistMod;
import theArcanist.cards.GenericResonantCard;
import theArcanist.cards.cardUtil.Resonance;

import static theArcanist.util.Wiz.*;

public class ResonatingPower extends AbstractArcanistPower implements OnReceivePowerPower {
    public static String POWER_ID = ArcanistMod.makeID(ResonatingPower.class.getSimpleName());
    public final static String POWER_MESSAGE = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS[1];

    Resonance resonance;

    public ResonatingPower(Resonance resonance) {
        super(POWER_ID, PowerType.BUFF, false, adp(), resonance.amount);
    }

    @Override
    public void updateDescription() {
        description = descriptionArray[0];
        description = description.replace("!A!", "#b" + amount);
    }

    @Override
    public boolean onReceivePower(AbstractPower pow, AbstractCreature target, AbstractCreature source) {
        if (target == owner && pow instanceof ResonatingPower)
            stackPower((ResonatingPower) pow);
        updateDescription();
        return true;
    }

    public void stackPower(ResonatingPower pow) {
        resonance.merge(pow.resonance);
        amount = resonance.amount;
    }

    @Override
    public void stackPower(int stackAmount) {
    }

    public void atEndOfTurn(boolean isPlayer) {
        GenericResonantCard card = new GenericResonantCard(resonance.resClone());
        topDeck(card);
        atb(new RemoveSpecificPowerAction(owner, owner, this));
    }
}