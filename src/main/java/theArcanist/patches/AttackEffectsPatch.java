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

public class AttackEffectsPatch {
    @SpirePatch2(
            clz = FlashAtkImgEffect.class,
            method = "loadImage"
    )
    public static class VfxPatch {
        public static SpireReturn<TextureAtlas.AtlasRegion> Prefix(FlashAtkImgEffect __instance) {
            AbstractGameAction.AttackEffect effect = ReflectionHacks.getPrivate(__instance, FlashAtkImgEffect.class, "effect");
            if (effect == ArcanistMod.Enums.WATER) {
                Texture texture = TexLoader.getTexture(ArcanistMod.WATER_EFFECT_FILE);
                TextureAtlas.AtlasRegion atReg = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
                return SpireReturn.Return(atReg);
            }
            if (effect == ArcanistMod.Enums.BLOOD) {
                Texture texture = TexLoader.getTexture(ArcanistMod.BLOOD_EFFECT_FILE);
                TextureAtlas.AtlasRegion atReg = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
                return SpireReturn.Return(atReg);
            }
            if (effect == ArcanistMod.Enums.ACID) {
                Texture texture = TexLoader.getTexture(ArcanistMod.ACID_EFFECT_FILE);
                TextureAtlas.AtlasRegion atReg = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
                return SpireReturn.Return(atReg);
            }
            if (effect == ArcanistMod.Enums.ICE) {
                Texture texture = TexLoader.getTexture(ArcanistMod.ICE_EFFECT_FILE);
                TextureAtlas.AtlasRegion atReg = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
                return SpireReturn.Return(atReg);
            }
            if (effect == ArcanistMod.Enums.SOUL_FIRE) {
                Texture texture = TexLoader.getTexture(ArcanistMod.SOUL_FIRE_EFFECT_FILE);
                TextureAtlas.AtlasRegion atReg = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
                return SpireReturn.Return(atReg);
            }
            if (effect == ArcanistMod.Enums.FIST) {
                Texture texture = TexLoader.getTexture(ArcanistMod.PHANTOM_FIST_EFFECT_FILE);
                TextureAtlas.AtlasRegion atReg = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
                return SpireReturn.Return(atReg);
            }
            if (effect == ArcanistMod.Enums.DARK_COIL) {
                Texture texture = TexLoader.getTexture(ArcanistMod.DARK_COIL_EFFECT_FILE);
                TextureAtlas.AtlasRegion atReg = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
                return SpireReturn.Return(atReg);
            }
            if (effect == ArcanistMod.Enums.DARK_WAVE) {
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch2(
            clz = DamageHeartEffect.class,
            method = "loadImage"
    )
    public static class OtherVfxPatch {
        public static SpireReturn<TextureAtlas.AtlasRegion> Prefix(DamageHeartEffect __instance) {
            AbstractGameAction.AttackEffect effect = ReflectionHacks.getPrivate(__instance, DamageHeartEffect.class, "effect");
            if (effect == ArcanistMod.Enums.WATER) {
                Texture texture = TexLoader.getTexture(ArcanistMod.WATER_EFFECT_FILE);
                TextureAtlas.AtlasRegion atReg = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
                return SpireReturn.Return(atReg);
            }
            if (effect == ArcanistMod.Enums.BLOOD) {
                Texture texture = TexLoader.getTexture(ArcanistMod.BLOOD_EFFECT_FILE);
                TextureAtlas.AtlasRegion atReg = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
                return SpireReturn.Return(atReg);
            }
            if (effect == ArcanistMod.Enums.ACID) {
                Texture texture = TexLoader.getTexture(ArcanistMod.ACID_EFFECT_FILE);
                TextureAtlas.AtlasRegion atReg = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
                return SpireReturn.Return(atReg);
            }
            if (effect == ArcanistMod.Enums.ICE) {
                Texture texture = TexLoader.getTexture(ArcanistMod.ICE_EFFECT_FILE);
                TextureAtlas.AtlasRegion atReg = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
                return SpireReturn.Return(atReg);
            }
            if (effect == ArcanistMod.Enums.SOUL_FIRE) {
                Texture texture = TexLoader.getTexture(ArcanistMod.SOUL_FIRE_EFFECT_FILE);
                TextureAtlas.AtlasRegion atReg = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
                return SpireReturn.Return(atReg);
            }
            if (effect == ArcanistMod.Enums.FIST) {
                Texture texture = TexLoader.getTexture(ArcanistMod.PHANTOM_FIST_EFFECT_FILE);
                TextureAtlas.AtlasRegion atReg = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
                return SpireReturn.Return(atReg);
            }
            if (effect == ArcanistMod.Enums.DARK_COIL) {
                Texture texture = TexLoader.getTexture(ArcanistMod.DARK_COIL_EFFECT_FILE);
                TextureAtlas.AtlasRegion atReg = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
                return SpireReturn.Return(atReg);
            }
            if (effect == ArcanistMod.Enums.DARK_WAVE) {
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
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
                CardCrawlGame.sound.play("ATTACK_POISON");
                return SpireReturn.Return();
            }
            if (effect == ArcanistMod.Enums.FIST) {
                CardCrawlGame.sound.play("BLUNT_HEAVY");
                return SpireReturn.Return();
            }
            if (effect == ArcanistMod.Enums.ICE) {
                CardCrawlGame.sound.play(ArcanistMod.COLD_KEY);
                return SpireReturn.Return();
            }
            if (effect == ArcanistMod.Enums.SOUL_FIRE) {
                CardCrawlGame.sound.play("ATTACK_FIRE");
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}