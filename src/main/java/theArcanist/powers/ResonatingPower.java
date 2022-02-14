package theArcanist.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;
import theArcanist.cards.GenericResonantCard;

import static java.lang.Math.max;
import static theArcanist.util.Wiz.*;

public class ResonatingPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID("Resonating");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final int DEDUCTION = 8;
    public static final int TYPE_BONUS = 4;

    public boolean cold = false;
    public boolean force = false;
    public boolean dark = false;
    public boolean fire = false;

    public ResonatingPower(AbstractCreature owner, int amount, boolean cold, boolean dark, boolean force, boolean fire, int jinx) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
        this.dark = dark;
        this.cold = cold;
        this.force = force;
        this.fire = fire;
        isTwoAmount = true;
        amount2 = jinx;
    }

    public void stackPower(ResonatingPower pow) {
        ArcanistMod.logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        amount += max(0, pow.amount - DEDUCTION);
        amount2 += pow.amount2;
        if (pow.force) {
            if (!force)
                force = true;
            else
                amount += TYPE_BONUS;
        }
        if (pow.cold) {
            if (!cold)
                cold = true;
            else
                amount += TYPE_BONUS;
        }
        if (pow.dark) {
            if (!dark)
                dark = true;
            else
                amount += TYPE_BONUS;
        }
        if (pow.fire) {
            if (!fire)
                fire = true;
            else
                amount += TYPE_BONUS;
        }
    }

    @Override
    public void stackPower(int stackAmount) {
    }

    public void atEndOfTurn(boolean isPlayer) {
        GenericResonantCard card = new GenericResonantCard(amount, cold, dark, force, fire, amount2);
        topDeck(card);
        atb(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void atEndOfRound() {
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}