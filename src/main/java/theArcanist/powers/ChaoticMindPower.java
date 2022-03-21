package theArcanist.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.actions.RapidsAction;

import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.atb;

public class ChaoticMindPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(ChaoticMindPower.class.getSimpleName());

    public ChaoticMindPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        atb(new RapidsAction(amount));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        atb(new RemoveSpecificPowerAction(adp(), adp(), this));
    }
}