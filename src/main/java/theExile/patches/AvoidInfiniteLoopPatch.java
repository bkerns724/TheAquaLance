package theExile.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.WaveOfTheHandPower;

public class AvoidInfiniteLoopPatch {
    private static boolean reaction = false;

    @SpirePatch(
            clz = AbstractPower.class,
            method = SpirePatch.CLASS
    )
    public static class ReactFields {
        public static SpireField<Boolean> reaction = new SpireField(() -> false);
    }

    @SpirePatch2(
            clz = WaveOfTheHandPower.class,
            method = "onGainedBlock"
    )
    public static class SetReactionVariable {
        @SpirePrefixPatch
        public static void prefix() {
            reaction = true;
        }

        @SpirePostfixPatch
        public static void postfix() {
            reaction = false;
        }
    }

    @SpirePatch2(
            clz = ApplyPowerAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {AbstractCreature.class, AbstractCreature.class, AbstractPower.class, int.class,
            boolean.class, AbstractGameAction.AttackEffect.class}
    )
    public static class MarkReactionPower {
        @SpirePrefixPatch
        public static void prefix(AbstractPower powerToApply) {
            if (reaction)
                ReactFields.reaction.set(powerToApply, true);
        }
    }
}
