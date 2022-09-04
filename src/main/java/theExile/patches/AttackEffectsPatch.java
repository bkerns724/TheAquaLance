package theExile.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.DamageHeartEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theExile.ExileMod;
import theExile.util.TexLoader;

import java.util.ArrayList;

public class AttackEffectsPatch {
    private static ArrayList<AbstractGameAction.AttackEffect> emptyEffects;

    @SpirePatch2(
            clz = FlashAtkImgEffect.class,
            method = "loadImage"
    )
    public static class VfxPatch {
        public static SpireReturn<TextureAtlas.AtlasRegion> Prefix(FlashAtkImgEffect __instance) {
            AbstractGameAction.AttackEffect effect = ReflectionHacks.getPrivate(__instance, FlashAtkImgEffect.class, "effect");
            if (emptyEffects.contains(effect))
                return SpireReturn.Return(null);
            TextureAtlas.AtlasRegion output = getImage(effect);
            if (output == null)
                return SpireReturn.Continue();
            return SpireReturn.Return(output);
        }
    }

    @SpirePatch2(
            clz = DamageHeartEffect.class,
            method = "loadImage"
    )
    public static class OtherVfxPatch {
        public static SpireReturn<TextureAtlas.AtlasRegion> Prefix(DamageHeartEffect __instance) {
            AbstractGameAction.AttackEffect effect = ReflectionHacks.getPrivate(__instance, DamageHeartEffect.class, "effect");
            if (emptyEffects.contains(effect))
                return SpireReturn.Return(null);
            TextureAtlas.AtlasRegion output = getImage(effect);
            if (output == null)
                return SpireReturn.Continue();
            return SpireReturn.Return(output);
        }
    }

    public static TextureAtlas.AtlasRegion getImage (AbstractGameAction.AttackEffect effect) {
        if (emptyEffects.contains(effect))
            return null;

        Texture texture;

        if (effect == ExileMod.Enums.LIGHTNING_S)
            texture = TexLoader.getTexture(ExileMod.LIGHTNING_EFFECT_FILE);
        else if (effect == ExileMod.Enums.WATER)
            texture = TexLoader.getTexture(ExileMod.WATER_EFFECT_FILE);
        else if (effect == ExileMod.Enums.BLOOD)
            texture = TexLoader.getTexture(ExileMod.BLOOD_EFFECT_FILE);
        else if (effect == ExileMod.Enums.ACID)
            texture = TexLoader.getTexture(ExileMod.ACID_EFFECT_FILE);
        else if (effect == ExileMod.Enums.ACID_M)
            texture = TexLoader.getTexture(ExileMod.ACID_M_EFFECT_FILE);
        else if (effect == ExileMod.Enums.ICE)
            texture = TexLoader.getTexture(ExileMod.ICE_EFFECT_FILE);
        else if (effect == ExileMod.Enums.ICE_M)
            texture = TexLoader.getTexture(ExileMod.ICE_M_EFFECT_FILE);
        else if (effect == ExileMod.Enums.FORCE)
            texture = TexLoader.getTexture(ExileMod.FORCE_EFFECT_FILE);
        else if (effect == ExileMod.Enums.FORCE_M)
            texture = TexLoader.getTexture(ExileMod.FORCE_M_EFFECT_FILE);
        else if (effect == ExileMod.Enums.ELDRITCH)
            texture = TexLoader.getTexture(ExileMod.DARK_EFFECT_FILE);
        else if (effect == ExileMod.Enums.ELDRITCH_M)
            texture = TexLoader.getTexture(ExileMod.DARK_M_EFFECT_FILE);
        else if (effect == ExileMod.Enums.RESONANT)
            texture = TexLoader.getTexture(ExileMod.RESONANT_EFFECT_FILE);
        else if (effect == ExileMod.Enums.RESONANT_M)
            texture = TexLoader.getTexture(ExileMod.RESONANT_M_EFFECT_FILE);
        else if (effect == ExileMod.Enums.RESONANT_L)
            texture = TexLoader.getTexture(ExileMod.RESONANT_L_EFFECT_FILE);
        else
            return null;

        return new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }

    @SpirePatch2(
            clz = FlashAtkImgEffect.class,
            method = "playSound"
    )
    @SpirePatch2(
            clz = DamageHeartEffect.class,
            method = "playSound"
    )
    public static class SfxPatch {
        public static SpireReturn Prefix(AbstractGameAction.AttackEffect effect) {
            if (effect == ExileMod.Enums.LIGHTNING_S) {
                CardCrawlGame.sound.play("ORB_PLASMA_CHANNEL");
                return SpireReturn.Return();
            }
            if (effect == ExileMod.Enums.WATER) {
                CardCrawlGame.sound.play("ATTACK_POISON");
                return SpireReturn.Return();
            }
            if (effect == ExileMod.Enums.BLOOD) {
                CardCrawlGame.sound.play("ATTACK_POISON");
                return SpireReturn.Return();
            }
            if (effect == ExileMod.Enums.ACID) {
                CardCrawlGame.sound.playV("ATTACK_POISON2", 0.7f);
                return SpireReturn.Return();
            }
            if (effect == ExileMod.Enums.ACID_M) {
                CardCrawlGame.sound.play("ATTACK_POISON2");
                return SpireReturn.Return();
            }
            if (effect == ExileMod.Enums.FORCE) {
                CardCrawlGame.sound.play("BLUNT_FAST");
                return SpireReturn.Return();
            }
            if (effect == ExileMod.Enums.FORCE_M) {
                CardCrawlGame.sound.play("BLUNT_HEAVY");
                return SpireReturn.Return();
            }
            if (effect == ExileMod.Enums.ICE) {
                CardCrawlGame.sound.play(ExileMod.COLD_KEY);
                return SpireReturn.Return();
            }
            if (effect == ExileMod.Enums.ICE_M) {
                CardCrawlGame.sound.playV(ExileMod.COLD_M_KEY, 0.75f);
                return SpireReturn.Return();
            }
            if (effect == ExileMod.Enums.ELDRITCH) {
                CardCrawlGame.sound.play("SLIME_ATTACK_2");
                return SpireReturn.Return();
            }
            if (effect == ExileMod.Enums.ELDRITCH_M) {
                CardCrawlGame.sound.play("POWER_CONSTRICTED");
                return SpireReturn.Return();
            }
            if (effect == ExileMod.Enums.RESONANT) {
                CardCrawlGame.sound.playAV("BELL", 0.2f, 0.7f);
                return SpireReturn.Return();
            }
            if (effect == ExileMod.Enums.RESONANT_M) {
                CardCrawlGame.sound.playA("BELL", 0.7f);
                return SpireReturn.Return();
            }
            if (effect == ExileMod.Enums.RESONANT_L) {
                CardCrawlGame.sound.playA("BELL", 1.5f);
                CardCrawlGame.sound.playAV("BELL", 1.5f, 0.5f);
                return SpireReturn.Return();
            }
            if (emptyEffects.contains(effect))
                return SpireReturn.Return(null);
            return SpireReturn.Continue();
        }
    }

    static {
        emptyEffects = new ArrayList<>();
        emptyEffects.add(ExileMod.Enums.DARK_WAVE);
        emptyEffects.add(ExileMod.Enums.LIGHTNING_M);
        emptyEffects.add(ExileMod.Enums.LIGHTNING_L);
        emptyEffects.add(ExileMod.Enums.DARK_WAVE_M);
        emptyEffects.add(ExileMod.Enums.DARK_WAVE_L);
        emptyEffects.add(ExileMod.Enums.FORCE_L);
        emptyEffects.add(ExileMod.Enums.ICE_L);
        emptyEffects.add(ExileMod.Enums.ELDRITCH_L);
        emptyEffects.add(ExileMod.Enums.BLUNT_MASSIVE);
        emptyEffects.add(ExileMod.Enums.SLASH_MASSIVE);
        emptyEffects.add(ExileMod.Enums.FIRE_M);
        emptyEffects.add(ExileMod.Enums.FIRE_L);
        emptyEffects.add(ExileMod.Enums.ACID_L);
    }
}