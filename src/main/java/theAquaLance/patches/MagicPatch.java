package theAquaLance.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.watcher.BlockReturnPower;
import com.megacrit.cardcrawl.relics.Boot;
import com.megacrit.cardcrawl.relics.Torii;
import theAquaLance.AquaLanceMod;

import static theAquaLance.util.Wiz.*;

public class MagicPatch {
    // So many powers/relics are if (!THORNS && !HP_LOSS) instead of if ATTACK
    // WHY?!?!  IT SAYS ATTACK ON THE CARDS/RELICS!  IT DOESN'T SAY NOT THORNS/HP_LOSS!
    @SpirePatch(clz = ThornsPower.class, method = "onAttacked")
    @SpirePatch(clz = ReactivePower.class, method = "onAttacked")
    @SpirePatch(clz = BlockReturnPower.class, method = "onAttacked")
    @SpirePatch(clz = StaticDischargePower.class, method = "onAttacked")
    @SpirePatch(clz = FlightPower.class, method = "onAttacked")
    @SpirePatch(clz = FlameBarrierPower.class, method = "onAttacked")
    public static class MagicOnAttackPatch {
        @SpirePrefixPatch
        public static SpireReturn<Integer> Prefix(AbstractPower __instance, DamageInfo info, int damage) {
            if (info.type == AquaLanceMod.Enums.MAGIC)
                return SpireReturn.Return(damage);
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = PlatedArmorPower.class, method = "wasHPLost")
    public static class MagicOnPlatedPatch {
        @SpirePrefixPatch
        public static SpireReturn Prefix(PlatedArmorPower __instance, DamageInfo info, int damage) {
            if (info.type == AquaLanceMod.Enums.MAGIC)
                return SpireReturn.Return(null);
            else
                return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = Boot.class, method = "onAttackToChangeDamage")
    public static class MagicBootPatch {
        @SpirePrefixPatch
        public static SpireReturn<Integer> Prefix(Boot __instance, DamageInfo info, int damage) {
            if (info.type == AquaLanceMod.Enums.MAGIC)
                return SpireReturn.Return(damage);
            else
                return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = Torii.class, method = "onAttacked")
    public static class MagicToriiPatch {
        @SpirePrefixPatch
        public static SpireReturn<Integer> Prefix(Torii __instance, DamageInfo info, int damage) {
            if (info.type == AquaLanceMod.Enums.MAGIC)
                return SpireReturn.Return(damage);
            else
                return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = FlightPower.class, method = "calculateDamageTakenAmount")
    public static class FlightDamagePatch {
        @SpirePrefixPatch
        public static SpireReturn<Float> Prefix(FlightPower __instance, float damage, DamageInfo.DamageType type) {
            if (type == AquaLanceMod.Enums.MAGIC)
                return SpireReturn.Return(damage);
            else
                return SpireReturn.Continue();
        }
    }
}