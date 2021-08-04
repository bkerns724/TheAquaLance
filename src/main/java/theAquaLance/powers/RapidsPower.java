package theAquaLance.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.actions.RapidsAction;

import static theAquaLance.util.Wiz.*;

public class RapidsPower extends AbstractEasyPower {
    public static String POWER_ID = AquaLanceMod.makeID("Rapids");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public RapidsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        atb(new RapidsAction(amount));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        atb(new RemoveSpecificPowerAction(adp(), adp(), this));
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        else
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[3] + amount + DESCRIPTIONS[4];
    }
}