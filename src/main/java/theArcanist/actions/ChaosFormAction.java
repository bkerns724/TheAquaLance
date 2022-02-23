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
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.watcher.DevotionPower;
import com.megacrit.cardcrawl.relics.CultistMask;
import com.megacrit.cardcrawl.relics.Sozu;
import theArcanist.cards.AbstractSigilCard;
import theArcanist.cards.Strike;

import java.util.ArrayList;
import java.util.Objects;

import static com.megacrit.cardcrawl.events.AbstractEvent.logMetricObtainRelic;
import static theArcanist.util.Wiz.*;

public class ChaosFormAction extends AbstractGameAction {

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

        for (int x = amount; x > 0; x = x - 3)
            if (x > 2 || x <= AbstractDungeon.miscRng.random(1, 3))
                doAction(false);

        tickDuration();
    }

    private void doAction(boolean simple) {
        int x;
        if (!simple)
            x = AbstractDungeon.miscRng.random(0, 13);
        else
            x = AbstractDungeon.miscRng.random(0, 8);

        switch(x) {
            case 0:
                att(new DrawCardAction(2));
                att(new GainEnergyAction(2));
                break;
            case 1:
                applyToSelf(new MayhemPower(adp(), 1));
                break;
            case 2:
                att(new IncreaseMaxOrbAction(3));
                applyToSelf(new FocusPower(adp(), 2));
                att(new ChannelAction(new Lightning()));
                att(new ChannelAction(new Frost()));
                att(new ChannelAction(new Lightning()));
                break;
            case 3:
                applyToSelf(new AfterImagePower(adp(), 1));
                applyToSelf(new ThousandCutsPower(adp(), 1));
                break;
            case 4:
                applyToSelf(new MetallicizePower(adp(), 6));
                break;
            case 5:
                applyToSelf(new DevotionPower(adp(), 4));
                break;
            case 6:
                applyToSelf(new StrengthPower(adp(), 3));
                break;
            case 7:
                applyToSelf(new DexterityPower(adp(), 3));
                break;
            case 8:
                ArrayList<AbstractMonster> list = getEnemies();
                int y = AbstractDungeon.miscRng.random(0, list.size() - 1);
                AbstractMonster mon = list.get(y);
                AbstractCard card = new Strike();
                card.calculateCardDamage(mon);
                att(new PepperSprayAction(mon, new DamageInfo(adp(), card.damage, DamageInfo.DamageType.NORMAL)));
                break;
            case 9:
                if (getDebuffCount(adp()) > 1)
                    att(new RemoveDebuffsAction(adp()));
                else
                    doAction(true);
                break;
            case 10:
                ArrayList<AbstractPotion> potionList = adp().potions;
                for (AbstractPotion pot : potionList)
                    if (pot instanceof PotionSlot && !adp().hasRelic(Sozu.ID))
                        adp().obtainPotion(AbstractDungeon.returnTotallyRandomPotion());
                else
                    doAction(true);
                break;
            case 11:
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
            case 12:
                ArrayList<AbstractCard> list3 = adp().hand.group;
                int count2 = 0;
                for (AbstractCard card2 : list3)
                    if (card2 instanceof AbstractSigilCard)
                        count2++;
                if (count2 > 1) {
                    for (AbstractCard card2 : list3)
                        att(new DiscardSpecificCardAction(card2, adp().hand));
                    att(new DrawCardAction(count2));
                }
                else
                    doAction(true);
                break;
            case 13:
                if (!adp().hasRelic(CultistMask.ID)) {
                    CultistMask r = new CultistMask();
                    logMetricObtainRelic("FaceTrader", "Trade", r);
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F, r);
                    applyToSelf(new RitualPower(adp(), 1, true));
                }
                else
                    doAction(true);
                break;
        }
    }
}
