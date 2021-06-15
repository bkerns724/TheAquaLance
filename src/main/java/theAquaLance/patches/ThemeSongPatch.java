package theAquaLance.patches;

import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.audio.TempMusic;
import theAquaLance.AquaLanceMod;
import theAquaLance.TheAquaLance;

import static theAquaLance.util.Wiz.adp;

public class ThemeSongPatch {
    @SpirePatch(
            clz = TempMusic.class,
            method = "getSong"
    )
    public static class InsertSongPatch {
        @SpirePrefixPatch
        public static SpireReturn<Music> Prefix(TempMusic __instance, String key) {
            if (key.equals("BOSS_ENDING") && adp().getCardColor() == TheAquaLance.Enums.AQUALANCE_TURQUOISE_COLOR)
                return SpireReturn.Return(MainMusic.newMusic(AquaLanceMod.THEME_OGG));
            else
                return SpireReturn.Continue();
        }
    }
}