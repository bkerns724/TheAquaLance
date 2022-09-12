package theExile.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;
import theExile.cards.Haunt;
import theExile.powers.JinxPower;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class HauntPatch {
    @SpirePatch2(
            clz = ApplyPowerAction.class,
            method = "update"
    )
    public static class HauntTrigger {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(ApplyPowerAction __instance) {
            AbstractPower pow = ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class, "powerToApply");
            if (adp() == null || !(pow instanceof JinxPower))
                return;
            for (AbstractCard c : adp().discardPile.group) {
                if (c instanceof Haunt)
                    atb(new DiscardToHandAction(c));
            }
        }
        public static class Locator extends SpireInsertLocator {
            private Locator() {}

            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "effectList");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }
}
