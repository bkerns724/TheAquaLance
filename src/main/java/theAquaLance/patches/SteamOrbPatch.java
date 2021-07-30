package theAquaLance.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import theAquaLance.relics.SteamOrb;

import static theAquaLance.util.Wiz.adp;

public class SteamOrbPatch {
    @SpirePatch(
            clz = DiscardAction.class,
            method = "update"
    )
    public static class DiscardActionPatch {
        @SpirePrefixPatch
        public static void Prefix(DiscardAction __instance) {
            if (adp().hasRelic(SteamOrb.ID))
                ReflectionHacks.setPrivate(__instance, DiscardAction.class, "isRandom", true);
        }
    }
}