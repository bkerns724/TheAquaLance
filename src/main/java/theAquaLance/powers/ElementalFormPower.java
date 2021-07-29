package theAquaLance.powers;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.cards.FormHelper;

import java.util.ArrayList;

import static theAquaLance.util.Wiz.*;

public class ElementalFormPower extends AbstractEasyPower {
    public static String POWER_ID = AquaLanceMod.makeID("ElementalForm");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private FormHelper helperCard = new FormHelper();

    public ElementalFormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
        helperCard.baseSecondDamage = amount;
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        Triggered();
    }

    @Override
    public void onManualDiscard() {
        Triggered();
    }

    public void Triggered() {
        ArrayList<AbstractMonster> monsters = getEnemies();
        AbstractMonster m = getRandomItem(monsters);
        helperCard.calculateCardDamage(m);
        flash();
        atb(new DamageAction(m, new DamageInfo(adp(), helperCard.secondDamage, AquaLanceMod.Enums.MAGIC),
                AquaLanceMod.Enums.WATER));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}