package theExile.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.potions.ExplosivePotion;
import javassist.CtBehavior;

import static theExile.util.Wiz.atb;

// Explosive Potions should have always been THORNS damage
public class ExplosivePotionPatch {
    @SpirePatch2(
            clz = ExplosivePotion.class,
            method = "use"
    )
    public static class MakeExplosivePotionThornsDamage {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static SpireReturn Insert(ExplosivePotion __instance) {
            atb(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(__instance.getPotency(),
                    true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
            return SpireReturn.Return();
        }
        public static class Locator extends SpireInsertLocator {
            private Locator() {}

            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(DamageInfo.class, "createDamageMatrix");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }
}
