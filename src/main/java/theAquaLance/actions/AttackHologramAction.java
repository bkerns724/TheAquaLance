package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theAquaLance.AquaLanceMod;

import static theAquaLance.util.Wiz.adp;

public class AttackHologramAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    public AttackHologramAction() {
        actionType = ActionType.CARD_MANIPULATION;
        duration = Settings.ACTION_DUR_FASTER;
    }

    public void update() {
        if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
            isDone = true;
        } else {
            if (duration == Settings.ACTION_DUR_FASTER) {
                int discardSize = adp().discardPile.getAttacks().group.size();
                if (discardSize == 0) {
                    isDone = true;
                    return;
                }
                else if (discardSize == 1) {
                    AbstractCard tmp = adp().discardPile.getAttacks().group.get(0);
                    adp().discardPile.removeCard(tmp);
                    adp().discardPile.moveToHand(tmp);
                }
                else {
                    AbstractDungeon.gridSelectScreen.open(adp().discardPile.getAttacks(), 1, TEXT[0],
                            false, false, false, false);
                    tickDuration();
                    return;
                }
            }

            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    adp().discardPile.removeCard(c);
                    adp().hand.moveToHand(c);
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            tickDuration();
        }
    }
    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(AquaLanceMod.makeID("AttackFromDiscardToHandAction"));
        TEXT = uiStrings.TEXT;
    }
}
