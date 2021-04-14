package theAquaLance.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;

import theAquaLance.cards.AbstractEmbedCard;

import static theAquaLance.util.Wiz.adp;

public class UseCardActionPatch {
    @SpirePatch(
            clz = UseCardAction.class,
            method = "update"
    )
    public static class UseCardActionInsertPatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static SpireReturn Insert(UseCardAction __instance) {
            // Remove embed cards from limbo so they don't go in ANY pile
            AbstractCard targetCard = ReflectionHacks.getPrivate(__instance, UseCardAction.class, "targetCard");
            if (targetCard instanceof AbstractEmbedCard) {
                if (!((AbstractEmbedCard)targetCard).hitArtifact && !((AbstractEmbedCard)targetCard).missedMonsters) {
                    targetCard.freeToPlayOnce = false;
                    targetCard.isInAutoplay = false;
                    targetCard.exhaustOnUseOnce = false;
                    targetCard.dontTriggerOnUseCard = false;
                    targetCard.stopGlowing();
                    targetCard.unhover();
                    targetCard.untip();
                    if (adp().limbo.contains(targetCard))
                        adp().limbo.removeCard(targetCard);
                    adp().cardInUse = null;
                    __instance.isDone = true;
                    return SpireReturn.Return(null);
                }
                else if (((AbstractEmbedCard)targetCard).hitArtifact) {
                    ((AbstractEmbedCard) targetCard).hitArtifact = false;
                    // perhaps an animation in here, move to discard, then a null spire return
                    return SpireReturn.Continue();
                }
                else {
                    ((AbstractEmbedCard)targetCard).missedMonsters = false;
                    return SpireReturn.Continue();
                }
            }
            else
                return SpireReturn.Continue();
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "isInAutoplay");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}