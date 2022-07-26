package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.watcher.MasterRealityPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import theExile.ExileMod;

import java.util.ArrayList;

public class StudyDiscoveryAction extends AbstractGameAction {
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ExileMod.makeID("ExileStudy"));
    public static final String[] TEXT = uiStrings.TEXT;
    private boolean retrieveCard;

    public StudyDiscoveryAction(int amount) {
        actionType = ActionType.CARD_MANIPULATION;
        duration = startDuration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
        retrieveCard = false;
    }

    public void update() {
        ArrayList<AbstractCard> generatedCards = generateCardChoices();

        if (duration == startDuration) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, TEXT[0], false);
            tickDuration();
        } else {
            if (!retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    if (AbstractDungeon.player.hasPower(MasterRealityPower.POWER_ID))
                        disCard.upgrade();

                    disCard.setCostForTurn(disCard.cost - 1);
                    disCard.current_x = -1000.0F * Settings.xScale;

                    if (AbstractDungeon.player.hand.size() < 10)
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard,
                                (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    else
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard,
                                (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));

                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }

                retrieveCard = true;
                isDone = true;
            }

            tickDuration();
        }
    }

    private ArrayList<AbstractCard> generateCardChoices() {
        ArrayList<AbstractCard> cards = new ArrayList<>();

        while(cards.size() != amount) {
            boolean dupe = false;
            AbstractCard tmp;

            tmp = AbstractDungeon.getCard(AbstractCard.CardRarity.RARE, AbstractDungeon.cardRandomRng).makeCopy();

            for (AbstractCard c : cards) {
                if (c.cardID.equals(tmp.cardID)) {
                    dupe = true;
                    break;
                }
            }

            if (!dupe)
                cards.add(tmp.makeCopy());
        }

        return cards;
    }
}