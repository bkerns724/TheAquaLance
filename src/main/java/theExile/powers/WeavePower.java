package theExile.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theExile.cards.AbstractExileCard;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.*;
import static theExile.cards.AbstractExileCard.elenum.*;
import static theExile.util.Wiz.*;

public class WeavePower extends AbstractExilePower implements OnReceivePowerPower {
    public static String POWER_ID = makeID(WeavePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private ArrayList<AbstractExileCard.elenum> elementList = new ArrayList<>();
    private boolean usedUp = false;

    public WeavePower(ArrayList<AbstractExileCard.elenum> elementList) {
        super(POWER_ID, PowerType.BUFF, false, adp(), -1);
        this.name = NAME;
        if (elementList != null)
            this.elementList.addAll(elementList);
        updateDescription();
    }

    @Override
    public boolean onReceivePower(AbstractPower pow, AbstractCreature target, AbstractCreature source) {
        if (target == owner && pow instanceof WeavePower) {
            elementList.addAll(((WeavePower) pow).elementList);
            elementList = (ArrayList<AbstractExileCard.elenum>) elementList.stream().distinct().collect(Collectors.toList());
        }
        updateDescription();
        return true;
    }

    @Override
    public void stackPower(int stackAmount) {
    }

    @Override
    public void updateDescription() {
        if (elementList == null) {
            description = "Should Not Be Seen";
            return;
        }
        if (elementList.size() == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1];

        StringBuilder builder = new StringBuilder();

        if (elementList.contains(ICE))
            builder.append(COLD_STRING + " ");
        if (elementList.contains(FIRE))
            builder.append(SOULFIRE_STRING + " ");
        if (elementList.contains(DARK))
            builder.append(DARK_STRING + " ");
        if (elementList.contains(FORCE))
            builder.append(FORCE_STRING + " ");
        if (elementList.contains(LIGHTNING))
            builder.append(LIGHTNING_STRING + " ");

        description = description.replace("!TYPE! ", builder.toString());
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (usedUp)
            return;
        if (card instanceof AbstractExileCard && card.type == CardType.ATTACK) {
            flash();
            ((AbstractExileCard) card).addModifier(elementList, true);
            atb(new RemoveSpecificPowerAction(adp(), adp(), this));
            usedUp = true;
        }
    }
}