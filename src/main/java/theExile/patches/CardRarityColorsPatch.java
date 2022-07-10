package theExile.patches;

import basemod.ReflectionHacks;
import basemod.patches.com.megacrit.cardcrawl.screens.SingleCardViewPopup.RenderCardDescriptorsSCV;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import theExile.ExileMod;
import theExile.TheExile;
import theExile.util.TexLoader;

import static theExile.util.Wiz.adp;

public class CardRarityColorsPatch {
    private static Texture BASIC_BANNER_512;
    private static Texture BASIC_ATK_512;
    private static Texture BASIC_SKILL_512;
    private static Texture BASIC_POWER_512;
    private static Texture BASIC_LEFT_512;
    private static Texture BASIC_CENTER_512;
    private static Texture BASIC_RIGHT_512;
    private static Texture BASIC_BANNER_1024;
    private static Texture BASIC_ATK_1024;
    private static Texture BASIC_SKILL_1024;
    private static Texture BASIC_POWER_1024;
    private static Texture BASIC_LEFT_1024;
    private static Texture BASIC_CENTER_1024;
    private static Texture BASIC_RIGHT_1024;
    private static Texture COMMON_BANNER_512;
    private static Texture COMMON_ATK_512;
    private static Texture COMMON_SKILL_512;
    private static Texture COMMON_POWER_512;
    private static Texture COMMON_LEFT_512;
    private static Texture COMMON_CENTER_512;
    private static Texture COMMON_RIGHT_512;
    private static Texture COMMON_BANNER_1024;
    private static Texture COMMON_ATK_1024;
    private static Texture COMMON_SKILL_1024;
    private static Texture COMMON_POWER_1024;
    private static Texture COMMON_LEFT_1024;
    private static Texture COMMON_CENTER_1024;
    private static Texture COMMON_RIGHT_1024;
    private static Texture SPECIAL_BANNER_512;
    private static Texture SPECIAL_ATK_512;
    private static Texture SPECIAL_SKILL_512;
    private static Texture SPECIAL_POWER_512;
    private static Texture SPECIAL_LEFT_512;
    private static Texture SPECIAL_CENTER_512;
    private static Texture SPECIAL_RIGHT_512;
    private static Texture SPECIAL_BANNER_1024;
    private static Texture SPECIAL_ATK_1024;
    private static Texture SPECIAL_SKILL_1024;
    private static Texture SPECIAL_POWER_1024;
    private static Texture SPECIAL_LEFT_1024;
    private static Texture SPECIAL_CENTER_1024;
    private static Texture SPECIAL_RIGHT_1024;
    private static Texture UNIQUE_BANNER_512;
    private static Texture UNIQUE_ATK_512;
    private static Texture UNIQUE_SKILL_512;
    private static Texture UNIQUE_POWER_512;
    private static Texture UNIQUE_LEFT_512;
    private static Texture UNIQUE_CENTER_512;
    private static Texture UNIQUE_RIGHT_512;
    private static Texture UNIQUE_BANNER_1024;
    private static Texture UNIQUE_ATK_1024;
    private static Texture UNIQUE_SKILL_1024;
    private static Texture UNIQUE_POWER_1024;
    private static Texture UNIQUE_LEFT_1024;
    private static Texture UNIQUE_CENTER_1024;
    private static Texture UNIQUE_RIGHT_1024;
    private static Texture STATUS_BANNER_512;
    private static Texture STATUS_SKILL_512;
    private static Texture STATUS_LEFT_512;
    private static Texture STATUS_CENTER_512;
    private static Texture STATUS_RIGHT_512;
    private static Texture STATUS_BANNER_1024;
    private static Texture STATUS_SKILL_1024;
    private static Texture STATUS_LEFT_1024;
    private static Texture STATUS_CENTER_1024;
    private static Texture STATUS_RIGHT_1024;
    private static Texture CURSE_BANNER_512;
    private static Texture CURSE_SKILL_512;
    private static Texture CURSE_LEFT_512;
    private static Texture CURSE_CENTER_512;
    private static Texture CURSE_RIGHT_512;
    private static Texture CURSE_BANNER_1024;
    private static Texture CURSE_SKILL_1024;
    private static Texture CURSE_LEFT_1024;
    private static Texture CURSE_CENTER_1024;
    private static Texture CURSE_RIGHT_1024;

    public static void initialize() {
        BASIC_BANNER_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/banner_basic.png");
        BASIC_ATK_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/frame_attack_basic.png");
        BASIC_SKILL_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/frame_skill_basic.png");
        BASIC_POWER_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/frame_power_basic.png");
        BASIC_LEFT_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/basic_left.png");
        BASIC_CENTER_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/basic_center.png");
        BASIC_RIGHT_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/basic_right.png");
        COMMON_BANNER_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/banner_common.png");
        COMMON_ATK_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/frame_attack_common.png");
        COMMON_SKILL_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/frame_skill_common.png");
        COMMON_POWER_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/frame_power_common.png");
        COMMON_LEFT_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/common_left.png");
        COMMON_CENTER_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/common_center.png");
        COMMON_RIGHT_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/common_right.png");
        SPECIAL_BANNER_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/banner_special.png");
        SPECIAL_ATK_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/frame_attack_special.png");
        SPECIAL_SKILL_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/frame_skill_special.png");
        SPECIAL_POWER_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/frame_power_special.png");
        SPECIAL_LEFT_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/special_left.png");
        SPECIAL_CENTER_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/special_center.png");
        SPECIAL_RIGHT_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/special_right.png");
        UNIQUE_BANNER_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/banner_unique.png");
        UNIQUE_ATK_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/frame_attack_unique.png");
        UNIQUE_SKILL_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/frame_skill_unique.png");
        UNIQUE_POWER_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/frame_power_unique.png");
        UNIQUE_LEFT_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/unique_left.png");
        UNIQUE_CENTER_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/unique_center.png");
        UNIQUE_RIGHT_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/unique_right.png");
        STATUS_BANNER_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/banner_status.png");
        STATUS_SKILL_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/frame_skill_status.png");
        STATUS_LEFT_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/status_left.png");
        STATUS_CENTER_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/status_center.png");
        STATUS_RIGHT_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/status_right.png");
        CURSE_BANNER_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/banner_curse.png");
        CURSE_SKILL_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/frame_skill_curse.png");
        CURSE_LEFT_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/curse_left.png");
        CURSE_CENTER_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/curse_center.png");
        CURSE_RIGHT_512 = TexLoader.getTexture("exilemodResources/images/coloredFrames/512/curse_right.png");

        BASIC_BANNER_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/banner_basic.png");
        BASIC_ATK_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/frame_attack_basic.png");
        BASIC_SKILL_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/frame_skill_basic.png");
        BASIC_POWER_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/frame_power_basic.png");
        BASIC_LEFT_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/basic_left.png");
        BASIC_CENTER_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/basic_center.png");
        BASIC_RIGHT_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/basic_right.png");
        COMMON_BANNER_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/banner_common.png");
        COMMON_ATK_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/frame_attack_common.png");
        COMMON_SKILL_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/frame_skill_common.png");
        COMMON_POWER_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/frame_power_common.png");
        COMMON_LEFT_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/common_left.png");
        COMMON_CENTER_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/common_center.png");
        COMMON_RIGHT_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/common_right.png");
        SPECIAL_BANNER_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/banner_special.png");
        SPECIAL_ATK_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/frame_attack_special.png");
        SPECIAL_SKILL_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/frame_skill_special.png");
        SPECIAL_POWER_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/frame_power_special.png");
        SPECIAL_LEFT_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/special_left.png");
        SPECIAL_CENTER_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/special_center.png");
        SPECIAL_RIGHT_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/special_right.png");
        UNIQUE_BANNER_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/banner_unique.png");
        UNIQUE_ATK_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/frame_attack_unique.png");
        UNIQUE_SKILL_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/frame_skill_unique.png");
        UNIQUE_POWER_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/frame_power_unique.png");
        UNIQUE_LEFT_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/unique_left.png");
        UNIQUE_CENTER_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/unique_center.png");
        UNIQUE_RIGHT_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/unique_right.png");
        STATUS_BANNER_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/banner_status.png");
        STATUS_SKILL_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/frame_skill_status.png");
        STATUS_LEFT_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/status_left.png");
        STATUS_CENTER_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/status_center.png");
        STATUS_RIGHT_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/status_right.png");
        CURSE_BANNER_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/banner_curse.png");
        CURSE_SKILL_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/frame_skill_curse.png");
        CURSE_LEFT_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/curse_left.png");
        CURSE_CENTER_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/curse_center.png");
        CURSE_RIGHT_1024 = TexLoader.getTexture("exilemodResources/images/coloredFrames/1024/curse_right.png");
    }

    @SpirePatch2(
            clz = AbstractCard.class,
            method = "renderBannerImage"
    )
    public static class BannerPatch {
        @SpirePrefixPatch
        public static SpireReturn bannerPrefix512(AbstractCard __instance, @ByRef SpriteBatch[] sb,
                                              float drawX, float drawY) {
            if (adp() != null && adp().chosenClass != TheExile.Enums.THE_EXILE && !ExileMod.isUniversalBanners())
                return SpireReturn.Continue();
            if (adp() == null && __instance.color != TheExile.Enums.EXILE_BLARPLE_COLOR && !ExileMod.isUniversalBanners())
                return SpireReturn.Continue();
            if (CardRarityColorsPatch.BASIC_BANNER_512 == null)
                initialize();
            ReflectionHacks.RMethod method = ReflectionHacks.privateMethod(AbstractCard.class, "renderHelper",
                    SpriteBatch.class, Color.class, TextureAtlas.AtlasRegion.class, float.class, float.class);
            Color color = ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
            Texture texture = CardRarityColorsPatch.BASIC_BANNER_512;
            switch (__instance.rarity) {
                case BASIC:
                    texture = CardRarityColorsPatch.BASIC_BANNER_512;
                    break;
                case SPECIAL:
                    texture = CardRarityColorsPatch.SPECIAL_BANNER_512;
                    break;
                case COMMON:
                    texture = CardRarityColorsPatch.COMMON_BANNER_512;
                    break;
                case UNCOMMON:
                    return SpireReturn.Continue();
                case RARE:
                    return SpireReturn.Continue();
            }
            // switch doesn't take my enum apparently
            if (__instance.rarity == ExileMod.Enums.UNIQUE)
                texture = CardRarityColorsPatch.UNIQUE_BANNER_512;
            if (__instance.type == AbstractCard.CardType.STATUS)
                texture = CardRarityColorsPatch.STATUS_BANNER_512;
            // Apparently some curses aren't curse rarity    ¯\_(ツ)_/¯
            // Some are special rarity so have this check after the special one
            if (__instance.type == AbstractCard.CardType.CURSE)
                texture = CardRarityColorsPatch.CURSE_BANNER_512;
            TextureAtlas.AtlasRegion img = texToAtReg(texture);
            img.offsetX = 94f;
            img.offsetY = 378f;
            img.originalHeight = 512;
            img.originalWidth = 512;
            method.invoke(__instance, sb[0], color, img, drawX, drawY);
            return SpireReturn.Return();
        }
    }

    @SpirePatch2(
            clz = AbstractCard.class,
            method = "renderAttackPortrait"
    )
    public static class AttackFramePatch {
        @SpirePrefixPatch
        public static SpireReturn attackFramePrefix512(AbstractCard __instance, @ByRef SpriteBatch[] sb, float x, float y) {
            if (adp() != null && adp().chosenClass != TheExile.Enums.THE_EXILE && !ExileMod.isUniversalBanners())
                return SpireReturn.Continue();
            if (adp() == null && __instance.color != TheExile.Enums.EXILE_BLARPLE_COLOR && !ExileMod.isUniversalBanners())
                return SpireReturn.Continue();
            if (CardRarityColorsPatch.BASIC_BANNER_512 == null)
                initialize();
            ReflectionHacks.RMethod method = ReflectionHacks.privateMethod(AbstractCard.class, "renderHelper",
                    SpriteBatch.class, Color.class, TextureAtlas.AtlasRegion.class, float.class, float.class);
            Color color = ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
            Texture texture = CardRarityColorsPatch.BASIC_ATK_512;
            switch (__instance.rarity) {
                case BASIC:
                    texture = CardRarityColorsPatch.BASIC_ATK_512;
                    break;
                case SPECIAL:
                    texture = CardRarityColorsPatch.SPECIAL_ATK_512;
                    break;
                case COMMON:
                    texture = CardRarityColorsPatch.COMMON_ATK_512;
                    break;
                case UNCOMMON:
                    return SpireReturn.Continue();
                case RARE:
                    return SpireReturn.Continue();
            }
            if (__instance.rarity == ExileMod.Enums.UNIQUE)
                texture = CardRarityColorsPatch.UNIQUE_ATK_512;
            TextureAtlas.AtlasRegion img = texToAtReg(texture);
            img.offsetX = 125f;
            img.offsetY = 219f;
            img.originalHeight = 512;
            img.originalWidth = 512;
            method.invoke(__instance, sb[0], color, img, x, y);
            return SpireReturn.Return();
        }
    }

    @SpirePatch2(
            clz = AbstractCard.class,
            method = "renderPowerPortrait"
    )
    public static class PowerFramePatch {
        @SpirePrefixPatch
        public static SpireReturn powerFramePrefix512(AbstractCard __instance, @ByRef SpriteBatch[] sb, float x, float y) {
            if (adp() != null && adp().chosenClass != TheExile.Enums.THE_EXILE && !ExileMod.isUniversalBanners())
                return SpireReturn.Continue();
            if (adp() == null && __instance.color != TheExile.Enums.EXILE_BLARPLE_COLOR && !ExileMod.isUniversalBanners())
                return SpireReturn.Continue();
            if (CardRarityColorsPatch.BASIC_BANNER_512 == null)
                initialize();
            ReflectionHacks.RMethod method = ReflectionHacks.privateMethod(AbstractCard.class, "renderHelper",
                    SpriteBatch.class, Color.class, TextureAtlas.AtlasRegion.class, float.class, float.class);
            Color color = ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
            Texture texture = CardRarityColorsPatch.BASIC_POWER_512;
            switch (__instance.rarity) {
                case BASIC:
                    texture = CardRarityColorsPatch.BASIC_POWER_512;
                    break;
                case SPECIAL:
                    texture = CardRarityColorsPatch.SPECIAL_POWER_512;
                    break;
                case COMMON:
                    texture = CardRarityColorsPatch.COMMON_POWER_512;
                    break;
                case UNCOMMON:
                    return SpireReturn.Continue();
                case RARE:
                    return SpireReturn.Continue();
            }
            if (__instance.rarity == ExileMod.Enums.UNIQUE)
                texture = CardRarityColorsPatch.UNIQUE_POWER_512;
            TextureAtlas.AtlasRegion img = texToAtReg(texture);
            img.offsetX = 121f;
            img.offsetY = 222f;
            img.originalHeight = 512;
            img.originalWidth = 512;
            method.invoke(__instance, sb[0], color, img, x, y);
            return SpireReturn.Return();
        }
    }

    @SpirePatch2(
            clz = AbstractCard.class,
            method = "renderSkillPortrait"
    )
    public static class SkillFramePatch {
        @SpirePrefixPatch
        public static SpireReturn skillFramePrefix512(AbstractCard __instance, @ByRef SpriteBatch[] sb, float x, float y) {
            if (adp() != null && adp().chosenClass != TheExile.Enums.THE_EXILE && !ExileMod.isUniversalBanners())
                return SpireReturn.Continue();
            if (adp() == null && __instance.color != TheExile.Enums.EXILE_BLARPLE_COLOR && !ExileMod.isUniversalBanners())
                return SpireReturn.Continue();
            if (CardRarityColorsPatch.BASIC_BANNER_512 == null)
                initialize();
            ReflectionHacks.RMethod method = ReflectionHacks.privateMethod(AbstractCard.class, "renderHelper",
                    SpriteBatch.class, Color.class, TextureAtlas.AtlasRegion.class, float.class, float.class);
            Color color = ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
            Texture texture = CardRarityColorsPatch.BASIC_SKILL_512;
            switch (__instance.rarity) {
                case BASIC:
                    texture = CardRarityColorsPatch.BASIC_SKILL_512;
                    break;
                case SPECIAL:
                    texture = CardRarityColorsPatch.SPECIAL_SKILL_512;
                    break;
                case COMMON:
                    texture = CardRarityColorsPatch.COMMON_SKILL_512;
                    break;
                case UNCOMMON:
                    return SpireReturn.Continue();
                case RARE:
                    return SpireReturn.Continue();
            }
            if (__instance.rarity == ExileMod.Enums.UNIQUE)
                texture = CardRarityColorsPatch.UNIQUE_SKILL_512;
            if (__instance.type == AbstractCard.CardType.STATUS)
                texture = CardRarityColorsPatch.STATUS_SKILL_512;
            if (__instance.type == AbstractCard.CardType.CURSE)
                texture = CardRarityColorsPatch.CURSE_SKILL_512;
            TextureAtlas.AtlasRegion img = texToAtReg(texture);
            img.offsetX = 124f;
            img.offsetY = 222f;
            img.originalHeight = 512;
            img.originalWidth = 512;
            method.invoke(__instance, sb[0], color, img, x, y);
            return SpireReturn.Return();
        }
    }

    @SpirePatch2(
            clz = AbstractCard.class,
            method = "renderDynamicFrame"
    )
    public static class DynamicFramePatch {
        @SpirePrefixPatch
        public static SpireReturn dynamicFramePrefix512(AbstractCard __instance, @ByRef SpriteBatch[] sb, float x, float y,
                                         float typeOffset, float typeWidth) {
            if (adp() != null && adp().chosenClass != TheExile.Enums.THE_EXILE && !ExileMod.isUniversalBanners())
                return SpireReturn.Continue();
            if (adp() == null && __instance.color != TheExile.Enums.EXILE_BLARPLE_COLOR && !ExileMod.isUniversalBanners())
                return SpireReturn.Continue();
            if (typeWidth <= 1.1f)
                return SpireReturn.Continue();
            if (CardRarityColorsPatch.BASIC_BANNER_512 == null)
                initialize();
            ReflectionHacks.RMethod method = ReflectionHacks.privateMethod(AbstractCard.class,
                    "dynamicFrameRenderHelper",
                    SpriteBatch.class, TextureAtlas.AtlasRegion.class, float.class, float.class, float.class, float.class);
            Color color = ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
            Texture textureL = CardRarityColorsPatch.BASIC_LEFT_512;
            Texture textureC = CardRarityColorsPatch.BASIC_CENTER_512;
            Texture textureR = CardRarityColorsPatch.BASIC_RIGHT_512;
            switch (__instance.rarity) {
                case BASIC:
                    textureL = CardRarityColorsPatch.BASIC_LEFT_512;
                    textureC = CardRarityColorsPatch.BASIC_CENTER_512;
                    textureR = CardRarityColorsPatch.BASIC_RIGHT_512;
                    break;
                case SPECIAL:
                    textureL = CardRarityColorsPatch.SPECIAL_LEFT_512;
                    textureC = CardRarityColorsPatch.SPECIAL_CENTER_512;
                    textureR = CardRarityColorsPatch.SPECIAL_RIGHT_512;
                    break;
                case COMMON:
                    textureL = CardRarityColorsPatch.COMMON_LEFT_512;
                    textureC = CardRarityColorsPatch.COMMON_CENTER_512;
                    textureR = CardRarityColorsPatch.COMMON_RIGHT_512;
                    break;
                case UNCOMMON:
                    return SpireReturn.Continue();
                case RARE:
                    return SpireReturn.Continue();
            }
            if (__instance.rarity == ExileMod.Enums.UNIQUE) {
                textureL = CardRarityColorsPatch.UNIQUE_LEFT_512;
                textureC = CardRarityColorsPatch.UNIQUE_CENTER_512;
                textureR = CardRarityColorsPatch.UNIQUE_RIGHT_512;
            }
            if (__instance.type == AbstractCard.CardType.STATUS) {
                textureL = CardRarityColorsPatch.STATUS_LEFT_512;
                textureC = CardRarityColorsPatch.STATUS_CENTER_512;
                textureR = CardRarityColorsPatch.STATUS_RIGHT_512;
            }
            if (__instance.type == AbstractCard.CardType.CURSE) {
                textureL = CardRarityColorsPatch.CURSE_LEFT_512;
                textureC = CardRarityColorsPatch.CURSE_CENTER_512;
                textureR = CardRarityColorsPatch.CURSE_RIGHT_512;
            }
            TextureAtlas.AtlasRegion imgL = texToAtReg(textureL);
            TextureAtlas.AtlasRegion imgC = texToAtReg(textureC);
            TextureAtlas.AtlasRegion imgR = texToAtReg(textureR);
            imgL.originalHeight = 512;
            imgL.originalWidth = 512;
            imgC.originalHeight = 512;
            imgC.originalWidth = 512;
            imgR.originalHeight = 512;
            imgR.originalWidth = 512;
            imgL.offsetX = 228;
            imgL.offsetY = 224;
            imgC.offsetX = 241;
            imgC.offsetY = 224;
            imgR.offsetX = 272;
            imgR.offsetY = 224;
            method.invoke(__instance, sb[0], imgC, x, y, 0.0F, typeWidth);
            method.invoke(__instance, sb[0], imgL, x, y, -typeOffset, 1.0F);
            method.invoke(__instance, sb[0], imgR, x, y, typeOffset, 1.0F);
            return SpireReturn.Return();
        }
    }

    @SpirePatch2(
            clz = SingleCardViewPopup.class,
            method = "renderCardBanner"
    )
    public static class LargeBannerPatch {
        @SpirePrefixPatch
        public static SpireReturn bannerPrefix1024(SingleCardViewPopup __instance, @ByRef SpriteBatch[] sb) {
            AbstractCard card = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
            if (adp() != null && adp().chosenClass != TheExile.Enums.THE_EXILE && !ExileMod.isUniversalBanners())
                return SpireReturn.Continue();
            if (adp() == null && card.color != TheExile.Enums.EXILE_BLARPLE_COLOR && !ExileMod.isUniversalBanners())
                return SpireReturn.Continue();
            if (CardRarityColorsPatch.BASIC_BANNER_512 == null)
                initialize();
            ReflectionHacks.RMethod method = ReflectionHacks.privateMethod(SingleCardViewPopup.class,
                    "renderHelper",
                    SpriteBatch.class, float.class, float.class, TextureAtlas.AtlasRegion.class);

            Texture texture = CardRarityColorsPatch.BASIC_BANNER_1024;
            switch (card.rarity) {
                case BASIC:
                    texture = CardRarityColorsPatch.BASIC_BANNER_1024;
                    break;
                case SPECIAL:
                    texture = CardRarityColorsPatch.SPECIAL_BANNER_1024;
                    break;
                case COMMON:
                    texture = CardRarityColorsPatch.COMMON_BANNER_1024;
                    break;
                case UNCOMMON:
                    return SpireReturn.Continue();
                case RARE:
                    return SpireReturn.Continue();
            }
            if (card.rarity == ExileMod.Enums.UNIQUE)
                texture = CardRarityColorsPatch.UNIQUE_BANNER_1024;
            if (card.type == AbstractCard.CardType.STATUS)
                texture = CardRarityColorsPatch.STATUS_BANNER_1024;
            if (card.type == AbstractCard.CardType.CURSE)
                texture = CardRarityColorsPatch.CURSE_BANNER_1024;
            TextureAtlas.AtlasRegion img = texToAtReg(texture);
            img.offsetX = 191f;
            img.offsetY = 741f;
            img.originalHeight = 1024;
            img.originalWidth = 1024;
            method.invoke(__instance, sb[0], Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, img);
            return SpireReturn.Return();
        }
    }

    @SpirePatch2(
            clz = SingleCardViewPopup.class,
            method = "renderFrame"
    )
    public static class LargeFramePatch {
        @SpirePrefixPatch
        public static SpireReturn framePrefix1024(SingleCardViewPopup __instance, @ByRef SpriteBatch[] sb) {
            AbstractCard card = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
            if (adp() != null && adp().chosenClass != TheExile.Enums.THE_EXILE && !ExileMod.isUniversalBanners())
                return SpireReturn.Continue();
            if (adp() == null && card.color != TheExile.Enums.EXILE_BLARPLE_COLOR && !ExileMod.isUniversalBanners())
                return SpireReturn.Continue();
            if (CardRarityColorsPatch.BASIC_BANNER_512 == null)
                initialize();

            ReflectionHacks.RMethod method = ReflectionHacks.privateMethod(SingleCardViewPopup.class,
                    "renderHelper",
                    SpriteBatch.class, float.class, float.class, TextureAtlas.AtlasRegion.class);

            ReflectionHacks.RMethod method2 = ReflectionHacks.privateMethod(SingleCardViewPopup.class,
                    "renderDynamicFrame",
                    SpriteBatch.class, float.class, float.class, float.class, float.class);

            TextureAtlas.AtlasRegion img = null;
            float tOffset = 0.0f;
            float tWidth = 0.0f;
            label39:
            switch (card.type) {
                case ATTACK:
                    tWidth = AbstractCard.typeWidthAttack;
                    tOffset = AbstractCard.typeOffsetAttack;
                    switch (card.rarity) {
                        case BASIC:
                            img = texToAtReg(BASIC_ATK_1024);
                            break label39;
                        case COMMON:
                            img = texToAtReg(COMMON_ATK_1024);
                            break label39;
                        case UNCOMMON:
                            img = ImageMaster.CARD_FRAME_ATTACK_UNCOMMON_L;
                            break label39;
                        case RARE:
                            img = ImageMaster.CARD_FRAME_ATTACK_RARE_L;
                            break label39;
                        case SPECIAL:
                            img = texToAtReg(SPECIAL_ATK_1024);;
                            break label39;
                        default:
                            if (card.rarity == ExileMod.Enums.UNIQUE) {
                                img = texToAtReg(UNIQUE_ATK_1024);
                                break label39;
                            }
                            img = ImageMaster.CARD_FRAME_ATTACK_COMMON_L;
                            break label39;
                    }
                case POWER:
                    tWidth = AbstractCard.typeWidthPower;
                    tOffset = AbstractCard.typeOffsetPower;
                    switch (card.rarity) {
                        case BASIC:
                            img = texToAtReg(BASIC_POWER_1024);
                            break label39;
                        case COMMON:
                            img = texToAtReg(COMMON_POWER_1024);
                            break label39;
                        case UNCOMMON:
                            img = ImageMaster.CARD_FRAME_POWER_UNCOMMON_L;
                            break label39;
                        case RARE:
                            img = ImageMaster.CARD_FRAME_POWER_RARE_L;
                            break label39;
                        case SPECIAL:
                            img = texToAtReg(SPECIAL_POWER_1024);;
                            break label39;
                        default:
                            if (card.rarity == ExileMod.Enums.UNIQUE) {
                                img = texToAtReg(UNIQUE_POWER_1024);
                                break label39;
                            }
                            img = ImageMaster.CARD_FRAME_ATTACK_COMMON_L;
                            break label39;
                    }
                case SKILL:
                    tWidth = AbstractCard.typeWidthSkill;
                    tOffset = AbstractCard.typeOffsetSkill;
                    switch (card.rarity) {
                        case BASIC:
                            img = texToAtReg(BASIC_SKILL_1024);
                            break label39;
                        case COMMON:
                            img = texToAtReg(COMMON_SKILL_1024);
                            break label39;
                        case UNCOMMON:
                            img = ImageMaster.CARD_FRAME_SKILL_UNCOMMON_L;
                            break label39;
                        case RARE:
                            img = ImageMaster.CARD_FRAME_SKILL_RARE_L;
                            break label39;
                        case SPECIAL:
                            img = texToAtReg(SPECIAL_SKILL_1024);;
                            break label39;
                        default:
                            if (card.rarity == ExileMod.Enums.UNIQUE) {
                                img = texToAtReg(UNIQUE_SKILL_1024);
                                break label39;
                            }
                            img = ImageMaster.CARD_FRAME_ATTACK_COMMON_L;
                            break label39;
                    }
                case CURSE:
                    tWidth = AbstractCard.typeWidthCurse;
                    tOffset = AbstractCard.typeOffsetCurse;
                    img = texToAtReg(CURSE_SKILL_1024);
                    break;
                case STATUS:
                    tWidth = AbstractCard.typeWidthStatus;
                    tOffset = AbstractCard.typeOffsetStatus;
                    img = texToAtReg(STATUS_SKILL_1024);
                    break;
                default:
                    tWidth = AbstractCard.typeWidthSkill;
                    tOffset = AbstractCard.typeOffsetSkill;
                    img = texToAtReg(BASIC_SKILL_1024);
            }
            img.originalHeight = 1024;
            img.originalWidth = 1024;

            if (card.type == AbstractCard.CardType.ATTACK) {
                img.offsetX = 253;
                img.offsetY = 442;
            }
            else if (card.type == AbstractCard.CardType.POWER) {
                img.offsetX = 246;
                img.offsetY = 448;
            }
            else {
                img.offsetX = 251;
                img.offsetY = 449;
            }

            method.invoke(__instance, sb[0], (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F, img);

            float[] tOffset2 = {tOffset};
            float[] tWidth2 = {tWidth};
            RenderCardDescriptorsSCV.Frame.Insert(__instance, sb[0], card, tOffset2, tWidth2);
            tOffset = tOffset2[0];
            tWidth = tWidth2[0];
            method2.invoke(__instance, sb[0], (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F, tOffset, tWidth);

            return SpireReturn.Return();
        }
    }

    @SpirePatch2(
            clz = SingleCardViewPopup.class,
            method = "renderDynamicFrame"
    )
    public static class LargeDynamicFramePatch {
        @SpirePrefixPatch
        public static SpireReturn dynamicFramePrefix1024(SingleCardViewPopup __instance, @ByRef SpriteBatch[] sb, float x, float y,
                                              float typeOffset, float typeWidth) {
            AbstractCard card = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
            if (adp() != null && adp().chosenClass != TheExile.Enums.THE_EXILE && !ExileMod.isUniversalBanners())
                return SpireReturn.Continue();
            if (adp() == null && card.color != TheExile.Enums.EXILE_BLARPLE_COLOR && !ExileMod.isUniversalBanners())
                return SpireReturn.Continue();

            if (CardRarityColorsPatch.BASIC_BANNER_512 == null)
                initialize();
            if (typeWidth <= 1.1f)
                return SpireReturn.Return();

            ReflectionHacks.RMethod method = ReflectionHacks.privateMethod(SingleCardViewPopup.class,
                    "dynamicFrameRenderHelper",
                    SpriteBatch.class, TextureAtlas.AtlasRegion.class, float.class, float.class);

            TextureAtlas.AtlasRegion imgC = null;
            TextureAtlas.AtlasRegion imgL = null;
            TextureAtlas.AtlasRegion imgR = null;

            switch(card.rarity) {
                case BASIC:
                    imgC = texToAtReg(BASIC_CENTER_1024);
                    imgL = texToAtReg(BASIC_LEFT_1024);
                    imgR = texToAtReg(BASIC_RIGHT_1024);
                    break;
                case COMMON:
                    imgC = texToAtReg(COMMON_CENTER_1024);
                    imgL = texToAtReg(COMMON_LEFT_1024);
                    imgR = texToAtReg(COMMON_RIGHT_1024);
                    break;
                case UNCOMMON:
                    imgC = ImageMaster.CARD_UNCOMMON_FRAME_MID_L;
                    imgL = ImageMaster.CARD_UNCOMMON_FRAME_LEFT_L;
                    imgR = ImageMaster.CARD_UNCOMMON_FRAME_RIGHT_L;
                    break;
                case RARE:
                    imgC = ImageMaster.CARD_RARE_FRAME_MID_L;
                    imgL = ImageMaster.CARD_RARE_FRAME_LEFT_L;
                    imgR = ImageMaster.CARD_RARE_FRAME_RIGHT_L;
                    break;
                case SPECIAL:
                    imgC = texToAtReg(SPECIAL_CENTER_1024);
                    imgL = texToAtReg(SPECIAL_LEFT_1024);
                    imgR = texToAtReg(SPECIAL_RIGHT_1024);
                    break;
                default:
                    imgC = texToAtReg(BASIC_CENTER_1024);
                    imgL = texToAtReg(BASIC_LEFT_1024);
                    imgR = texToAtReg(BASIC_RIGHT_1024);
                    break;
            }

            if (card.type == AbstractCard.CardType.STATUS) {
                imgC = texToAtReg(STATUS_CENTER_1024);
                imgL = texToAtReg(STATUS_LEFT_1024);
                imgR = texToAtReg(STATUS_RIGHT_1024);
            }
            if (card.type == AbstractCard.CardType.CURSE) {
                imgC = texToAtReg(CURSE_CENTER_1024);
                imgL = texToAtReg(CURSE_LEFT_1024);
                imgR = texToAtReg(CURSE_RIGHT_1024);
            }
            if (card.rarity == ExileMod.Enums.UNIQUE) {
                imgC = texToAtReg(UNIQUE_CENTER_1024);
                imgL = texToAtReg(UNIQUE_LEFT_1024);
                imgR = texToAtReg(UNIQUE_RIGHT_1024);
            }

            imgC.originalWidth = 1024;
            imgL.originalWidth = 1024;
            imgR.originalWidth = 1024;
            imgC.originalHeight = 1024;
            imgL.originalHeight = 1024;
            imgR.originalHeight = 1024;
            imgC.offsetX = 483;
            imgC.offsetY = 449;
            imgL.offsetX = 454;
            imgL.offsetY = 449;
            imgR.offsetX = 545;
            imgR.offsetY = 449;

            method.invoke(__instance, sb[0], imgC, 0.0F, typeWidth);
            method.invoke(__instance, sb[0], imgL, -typeOffset, 1.0F);
            method.invoke(__instance, sb[0], imgR, typeOffset, 1.0F);
            return SpireReturn.Return();
        }
    }

    public static TextureAtlas.AtlasRegion texToAtReg(Texture texture) {
        return new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }
}
