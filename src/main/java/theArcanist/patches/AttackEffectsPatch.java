package theArcanist.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.DamageHeartEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theArcanist.ArcanistMod;
import theArcanist.util.TexLoader;

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

        if (effect == ArcanistMod.Enums.WATER)
            texture = TexLoader.getTexture(ArcanistMod.WATER_EFFECT_FILE);
        else if (effect == ArcanistMod.Enums.BLOOD)
            texture = TexLoader.getTexture(ArcanistMod.BLOOD_EFFECT_FILE);
        else if (effect == ArcanistMod.Enums.ACID)
            texture = TexLoader.getTexture(ArcanistMod.ACID_EFFECT_FILE);
        else if (effect == ArcanistMod.Enums.ICE)
            texture = TexLoader.getTexture(ArcanistMod.ICE_EFFECT_FILE);
        else if (effect == ArcanistMod.Enums.ICE_M)
            texture = TexLoader.getTexture(ArcanistMod.ICE_M_EFFECT_FILE);
        else if (effect == ArcanistMod.Enums.SOUL_FIRE)
            texture = TexLoader.getTexture(ArcanistMod.SOUL_FIRE_EFFECT_FILE);
        else if (effect == ArcanistMod.Enums.FORCE)
            texture = TexLoader.getTexture(ArcanistMod.FORCE_EFFECT_FILE);
        else if (effect == ArcanistMod.Enums.FORCE_M)
            texture = TexLoader.getTexture(ArcanistMod.FORCE_M_EFFECT_FILE);
        else if (effect == ArcanistMod.Enums.DARK)
            texture = TexLoader.getTexture(ArcanistMod.DARK_EFFECT_FILE);
        else if (effect == ArcanistMod.Enums.DARK_M)
            texture = TexLoader.getTexture(ArcanistMod.DARK_M_EFFECT_FILE);
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
            if (effect == ArcanistMod.Enums.WATER) {
                CardCrawlGame.sound.play("ATTACK_POISON");
                return SpireReturn.Return();
            }
            if (effect == ArcanistMod.Enums.BLOOD) {
                CardCrawlGame.sound.play("ATTACK_POISON");
                return SpireReturn.Return();
            }
            if (effect == ArcanistMod.Enums.ACID) {
                CardCrawlGame.sound.play("ATTACK_POISON2");
                return SpireReturn.Return();
            }
            if (effect == ArcanistMod.Enums.FORCE) {
                CardCrawlGame.sound.play("BLUNT_FAST");
                return SpireReturn.Return();
            }
            if (effect == ArcanistMod.Enums.FORCE_M) {
                CardCrawlGame.sound.play("BLUNT_HEAVY");
                return SpireReturn.Return();
            }
            if (effect == ArcanistMod.Enums.ICE) {
                CardCrawlGame.sound.play(ArcanistMod.COLD_KEY);
                return SpireReturn.Return();
            }
            if (effect == ArcanistMod.Enums.ICE_M) {
                CardCrawlGame.sound.play(ArcanistMod.COLD_M_KEY);
                return SpireReturn.Return();
            }
            if (effect == ArcanistMod.Enums.DARK) {
                CardCrawlGame.sound.play("SLIME_ATTACK_2");
                return SpireReturn.Return();
            }
            if (effect == ArcanistMod.Enums.DARK_M) {
                CardCrawlGame.sound.play("POWER_CONSTRICTED");
                return SpireReturn.Return();
            }
            if (effect == ArcanistMod.Enums.SOUL_FIRE) {
                CardCrawlGame.sound.play("ATTACK_FIRE");
                return SpireReturn.Return();
            }
            if (emptyEffects.contains(effect))
                return SpireReturn.Return(null);
            return SpireReturn.Continue();
        }
    }

    static {
        emptyEffects = new ArrayList<>();
        emptyEffects.add(ArcanistMod.Enums.DARK_WAVE);
        emptyEffects.add(ArcanistMod.Enums.DARK_WAVE_M);
        emptyEffects.add(ArcanistMod.Enums.DARK_WAVE_L);
        emptyEffects.add(ArcanistMod.Enums.FORCE_L);
        emptyEffects.add(ArcanistMod.Enums.ICE_L);
        emptyEffects.add(ArcanistMod.Enums.DARK_L);
        emptyEffects.add(ArcanistMod.Enums.SOUL_FIRE_M);
        emptyEffects.add(ArcanistMod.Enums.SOUL_FIRE_L);
        emptyEffects.add(ArcanistMod.Enums.DARK_WAVE_L);
        emptyEffects.add(ArcanistMod.Enums.BLUNT_MASSIVE);
        emptyEffects.add(ArcanistMod.Enums.SLASH_MASSIVE);
    }
}