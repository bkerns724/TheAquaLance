package theExile.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CtBehavior;

import static theExile.util.Wiz.adp;

// If a card can be used on some creatures but not others, this turns the arrow grey if you're hovering
//   a monster you can't use it on
public class CantUseGreyArrowPatch {
    @SpirePatch2(
            clz = AbstractPlayer.class,
            method = "renderTargetingUi"
    )
    public static class FatalTrigger {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AbstractPlayer __instance, SpriteBatch sb) {
            AbstractMonster m = ReflectionHacks.getPrivate(__instance, AbstractPlayer.class, "hoveredMonster");
            AbstractCard card = ReflectionHacks.getPrivate(__instance, AbstractPlayer.class, "hoveredCard");
            if (!card.canUse(adp(), m))
                sb.setColor(Color.WHITE.cpy());
        }
        public static class Locator extends SpireInsertLocator {
            private Locator() {}

            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "arrowTmp");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }
}
