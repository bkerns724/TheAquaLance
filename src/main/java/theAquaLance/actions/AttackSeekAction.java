package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theAquaLance.AquaLanceMod;

import static theAquaLance.util.Wiz.adp;

public class AttackSeekAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    public AttackSeekAction() {
        actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
            isDone = true;
        } else {
            if (duration == Settings.ACTION_DUR_FASTER) {
                int drawSize = adp().drawPile.getAttacks().group.size();
                if (drawSize == 0) {
                    isDone = true;
                    return;
                }
                else if (drawSize == 1) {
                    AbstractCard tmp = adp().drawPile.getAttacks().group.get(0);
                    adp().drawPile.removeCard(tmp);
                    adp().drawPile.moveToHand(tmp);
                }
                else {
                    AbstractDungeon.gridSelectScreen.open(adp().drawPile.getAttacks(), 1, TEXT[0],
                            false, false, false, false);
                    tickDuration();
                    return;
                }
            }

            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    adp().drawPile.removeCard(c);
                    adp().hand.moveToHand(c);
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            tickDuration();
        }
    }
    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(AquaLanceMod.makeID("AttackFromDrawToHandAction"));
        TEXT = uiStrings.TEXT;
    }
}
