package theAquaLance.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.powers.AbstractEasyPower;
import theAquaLance.powers.EmbedPower;

import java.util.ArrayList;

import static theAquaLance.util.Wiz.adp;
import static theAquaLance.util.Wiz.getEnemies;

public class DiscardHookPatch {
    @SpirePatch(
        clz = GameActionManager.class,
        method = SpirePatch.CLASS
    )
    public static class GameActionManagerField {
        public static SpireField<Integer> sigilsThisCombat = new SpireField<>(() -> 0);
    }

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
                        if (po instanceof EmbedPower)
                            ((EmbedPower) po).onManualDiscard();
                    }
                }
                for (AbstractPower p : adp().powers) {
                    if (p instanceof AbstractEasyPower)
                        ((AbstractEasyPower) p).onManualDiscard();
                }
            }
        }
    }

    @SpirePatch(
            clz = GameActionManager.class,
            method = "clear"
    )
    public static class GameActionManagerClearPatch {
        public static void Prefix(GameActionManager __instance) {
            GameActionManagerField.sigilsThisCombat.set(__instance, 0);
        }
    }
}