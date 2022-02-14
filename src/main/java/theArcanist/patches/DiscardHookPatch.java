package theArcanist.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.powers.AbstractArcanistPower;

import java.util.ArrayList;

import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.getEnemies;

public class DiscardHookPatch {
    @SpirePatch(
            clz = GameActionManager.class,
            method = "incrementDiscard"
    )
    public static class GameActionManagerIncrementDiscardPatch{
        public static void Postfix(boolean endOfTurn){
            // Need to trigger powers when a card is manually discarded.
            if (!AbstractDungeon.actionManager.turnHasEnded && !endOfTurn) {
                ArrayList<AbstractMonster> enemies = getEnemies();
                for (AbstractMonster m : enemies) {
                    for (AbstractPower po : m.powers) {
                        if (po instanceof AbstractArcanistPower) {
                            ((AbstractArcanistPower) po).onManualDiscard();
                        }
                    }
                }
                for (AbstractPower p : adp().powers) {
                    if (p instanceof AbstractArcanistPower) {
                        ((AbstractArcanistPower) p).onManualDiscard();
                    }
                }
            }
        }
    }
}