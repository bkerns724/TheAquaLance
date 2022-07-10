package theExile.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theExile.ExileMod;
import theExile.cards.AbstractExileCard;
import theExile.cards.GenericResonantCard;
import theExile.cards.cardUtil.Resonance;

import static theExile.util.Wiz.*;

public class ResonatingPower extends AbstractExilePower implements OnReceivePowerPower {
    public static String POWER_ID = ExileMod.makeID(ResonatingPower.class.getSimpleName());
    public final static String POWER_MESSAGE = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS[1];

    Resonance resonance;

    public ResonatingPower(Resonance resonance) {
        super(POWER_ID, PowerType.BUFF, false, adp(), resonance.amount);
        this.resonance = resonance;
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
        for (AbstractExileCard.elenum e : resonance.damageMods)
            card.addModifier(e);
        card.applyPowers();
        card.initializeDescription();
        topDeck(card);
        atb(new RemoveSpecificPowerAction(owner, owner, this));
    }
}