package theArcanist.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import javassist.CtBehavior;
import theArcanist.cards.AbstractArcanistCard;
import theArcanist.powers.OnFatalPower;

import static theArcanist.util.Wiz.adp;

// Can't do things with actions if you use this hook, have to directly do them since death clears the queue.
public class OnDeathPatch {
    @SpirePatch2(
            clz = AbstractMonster.class,
            method = "die",
            paramtypez = boolean.class
    )
    public static class FatalTrigger {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AbstractMonster __instance, boolean triggerRelics) {
            if (!__instance.halfDead && !__instance.hasPower(MinionPower.POWER_ID)) {
                for (AbstractPower pow : adp().powers)
                    if (pow instanceof OnFatalPower)
                        ((OnFatalPower) pow).onFatal(__instance);
            }

            for (AbstractCard card : AbstractDungeon.player.discardPile.group)
                if (card instanceof AbstractArcanistCard)
                    ((AbstractArcanistCard) card).triggerOnDeath();

            for (AbstractCard card : AbstractDungeon.player.drawPile.group)
                if (card instanceof AbstractArcanistCard)
                    ((AbstractArcanistCard) card).triggerOnDeath();
        }
        public static class Locator extends SpireInsertLocator {
            private Locator() {}

            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(AbstractDungeon.class, "getMonsters");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }
}
