package theAquaLance.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.powers.AbstractEasyPower;
import theAquaLance.relics.SteamOrb;

import static theAquaLance.util.Wiz.adp;

public class DiscardActionPatch {
    @SpirePatch(
            clz = DiscardAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {
                    AbstractCreature.class,
                    AbstractCreature.class,
                    int.class,
                    boolean.class,
                    boolean.class
            }
    )
    public static class DiscardActionPrefixPatch {
        public static void Prefix(DiscardAction __instance, AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean endTurn) {
            if (adp().hasRelic(SteamOrb.ID))
                isRandom = true;
        }
    }
}