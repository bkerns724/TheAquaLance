package theAquaLance.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.powers.AbstractEasyPower;
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