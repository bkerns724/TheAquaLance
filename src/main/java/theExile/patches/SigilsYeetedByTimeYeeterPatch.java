package theExile.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theExile.cards.AbstractExileCard;

import static theExile.util.Wiz.adp;

@SpirePatch2(
        clz = GameActionManager.class,
        method = "callEndTurnEarlySequence"
)
public class SigilsYeetedByTimeYeeterPatch {
    @SpirePrefixPatch
    public static SpireReturn prefix() {
        if (adp() == null)
            return SpireReturn.Continue();
        for (AbstractCard c : adp().hand.group) {
            if (c instanceof AbstractExileCard)
                ((AbstractExileCard) c).beingDiscarded = false;
        }
        for (AbstractCard c : adp().drawPile.group) {
            if (c instanceof AbstractExileCard)
                ((AbstractExileCard) c).beingDiscarded = false;
        }
        for (AbstractCard c : adp().discardPile.group) {
            if (c instanceof AbstractExileCard)
                ((AbstractExileCard) c).beingDiscarded = false;
        }
        for (AbstractCard c : adp().exhaustPile.group) {
            if (c instanceof AbstractExileCard)
                ((AbstractExileCard) c).beingDiscarded = false;
        }
        for (AbstractCard c : adp().limbo.group) {
            if (c instanceof AbstractExileCard)
                ((AbstractExileCard) c).beingDiscarded = false;
        }
        for (CardQueueItem i : AbstractDungeon.actionManager.cardQueue) {
            if (i.card instanceof AbstractExileCard)
                ((AbstractExileCard) i.card).beingDiscarded = false;
        }
        return SpireReturn.Continue();
    }
}