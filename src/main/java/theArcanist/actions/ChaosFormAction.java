package theArcanist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.watcher.DevotionPower;
import theArcanist.cards.AbstractArcanistCard;

import java.util.ArrayList;

import static theArcanist.util.Wiz.*;

public class ChaosFormAction extends AbstractGameAction {
    public static final int PEPPER_SPRAY_DAMAGE = 8;

    public ChaosFormAction(int amount)
    {
        duration = DEFAULT_DURATION;
        this.amount = amount;
    }

    @Override
    public void update() {
        if (duration != DEFAULT_DURATION) {
            isDone = true;
            return;
        }

        for (int x = 0; x < amount; x++)
            doAction(false);

        isDone = true;
    }

    private void doAction(boolean simple) {
        int x;
        if (!simple)
            x = AbstractDungeon.miscRng.random(0, 10);
        else
            x = AbstractDungeon.miscRng.random(0, 6);

        switch(x) {
            case 0:
                att(new DrawCardAction(2));
                att(new GainEnergyAction(2));
                break;
            case 1:
                att(new ChannelAction(new Lightning()));
                att(new ChannelAction(new Frost()));
                att(new ChannelAction(new Lightning()));
                att(new IncreaseMaxOrbAction(3));
                applyToSelfTop(new FocusPower(adp(), 2));
                break;
            case 2:
                applyToSelfTop(new AfterImagePower(adp(), 1));
                applyToSelfTop(new ThousandCutsPower(adp(), 1));
                break;
            case 3:
                applyToSelfTop(new MetallicizePower(adp(), 6));
                break;
            case 4:
                applyToSelfTop(new DevotionPower(adp(), 4));
                break;
            case 5:
                applyToSelfTop(new StrengthPower(adp(), 3));
                break;
            case 6:
                applyToSelfTop(new DexterityPower(adp(), 3));
                break;
            case 7:
                ArrayList<AbstractMonster> list = getEnemies();
                int y = AbstractDungeon.miscRng.random(0, list.size() - 1);
                if (list.size() == 0)
                    break;
                AbstractMonster mon = list.get(y);
                if (mon == null)
                    break;
                att(new PepperSprayAction(mon, new DamageInfo(adp(), PEPPER_SPRAY_DAMAGE, DamageInfo.DamageType.THORNS)));
                break;
            case 8:
                if (getDebuffCount(adp()) > 1)
                    att(new RemoveDebuffsAction(adp()));
                else
                    doAction(true);
                break;
            case 9:
                ArrayList<AbstractCard> list2 = adp().hand.group;
                int count = 0;
                for (AbstractCard card2 : list2)
                    if (card2.type == AbstractCard.CardType.STATUS || card2.type == AbstractCard.CardType.CURSE
                            || card2.rarity == AbstractCard.CardRarity.BASIC)
                        count++;
                if (count > 1) {
                    for (AbstractCard card2 : list2)
                        att(new DiscardSpecificCardAction(card2, adp().hand));
                    att(new DrawCardAction(count));
                }
                else
                    doAction(true);
                break;
            case 10:
                ArrayList<AbstractCard> list3 = adp().hand.group;
                int count2 = 0;
                for (AbstractCard card2 : list3)
                    if (card2 instanceof AbstractArcanistCard &&
                            ((AbstractArcanistCard) card2).sigil)
                        count2++;
                if (count2 > 1) {
                    for (AbstractCard card2 : list3)
                        att(new DiscardSpecificCardAction(card2, adp().hand));
                    att(new DrawCardAction(count2));
                }
                else
                    doAction(true);
                break;
        }
    }
}
