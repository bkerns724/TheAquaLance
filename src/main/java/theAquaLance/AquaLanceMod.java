package theAquaLance;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.relics.DeadBranch;
import com.megacrit.cardcrawl.relics.StrangeSpoon;
import com.megacrit.cardcrawl.relics.UnceasingTop;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theAquaLance.cards.AbstractEasyCard;
import theAquaLance.cards.cardvars.SecondDamage;
import theAquaLance.cards.cardvars.SecondMagicNumber;
import theAquaLance.potions.*;
import theAquaLance.relics.AbstractEasyRelic;
import theAquaLance.relics.RuneOfIce;
import theAquaLance.relics.RuneOfLivingWater;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static theAquaLance.TheAquaLance.Enums.THE_AQUALANCE;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class AquaLanceMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
        AddAudioSubscriber,
        PostDungeonInitializeSubscriber {

    public static final String SETTINGS_FILE = "AqualanceModSettings";

    public static final String modID = "aqualancemod";
    public static final String JSON_PRE = "AquaLanceMod:";

    public static Logger logger = LogManager.getLogger(AquaLanceMod.class.getName());

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static final String THEME_SONG_SETTING = "ThemeSongSetting";
    public static final String THEME_SONG_UISTRING = "ThemeSongConfig";

    public static final String EXTENDED_CUT_SETTING = "ExtendedCutSetting";
    public static final String EXTENDED_CUT_UISTRING = "ExtendedCutConfig";

    private static SpireConfig modConfig = null;

    public static final Color AQUALANCE_EYE_COLOR = CardHelper.getColor(64, 224, 208);

    public static final String SHOULDER1 = modID + "Resources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = modID + "Resources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = modID + "Resources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = modID + "Resources/images/512/attack.png";
    private static final String SKILL_S_ART = modID + "Resources/images/512/skill.png";
    private static final String POWER_S_ART = modID + "Resources/images/512/power.png";
    private static final String CARD_ENERGY_S = modID + "Resources/images/512/energy.png";
    private static final String TEXT_ENERGY = modID + "Resources/images/512/text_energy.png";
    private static final String ATTACK_L_ART = modID + "Resources/images/1024/attack.png";
    private static final String SKILL_L_ART = modID + "Resources/images/1024/skill.png";
    private static final String POWER_L_ART = modID + "Resources/images/1024/power.png";
    private static final String CARD_ENERGY_L = modID + "Resources/images/1024/energy.png";
    private static final String CHARSELECT_BUTTON = modID + "Resources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = modID + "Resources/images/charSelect/charBG.png";
    public static final String WATER_EFFECT_FILE = modID + "Resources/images/vfx/Water.png";
    public static final String BLOOD_EFFECT_FILE = modID + "Resources/images/vfx/Blood.png";

    public static final String THEME_KEY = makeID("Theme Song");
    public static final String THEME_OGG = modID + "Resources/audio/Theme2.ogg";

    public static final String STAB_KEY = makeID("Stab Sound");
    public static final String STAB_OGG = modID + "Resources/audio/Stab.ogg";

    public static final String MULTI_STAB_KEY = makeID("Multi Stab Sound");
    public static final String MULTI_STAB_OGG = modID + "Resources/audio/MultiStab.ogg";

    public static final String SPLASH_KEY = makeID("Splash Sound");
    public static final String SPLASH_OGG = modID + "Resources/audio/Splash.ogg";

    public static final String WATERFALL_KEY = makeID("Waterfall Sound");
    public static final String WATERFALL_OGG = modID + "Resources/audio/Waterfall.ogg";

    public static final String FOOTSTEPS_KEY = makeID("Footsteps Sound");
    public static final String FOOTSTEPS_OGG = modID + "Resources/audio/Footsteps.ogg";

    public static final String TRUCK_KEY = makeID("Truck Sound");
    public static final String TRUCK_OGG = modID + "Resources/audio/Truck.ogg";

    public static final String FIRE_KEY = makeID("Fire Sound");
    public static final String FIRE_OGG = modID + "Resources/audio/Fire.ogg";

    private static final String BADGE_IMG = modID + "Resources/images/Badge.png";
    private static final String[] REGISTRATION_STRINGS = {
            "The AquaLance", "Bryan", "This mod adds a new character, the AquaLance."
    };

    public AquaLanceMod() {
        BaseMod.addColor(TheAquaLance.Enums.AQUALANCE_TURQUOISE_COLOR, AQUALANCE_EYE_COLOR, AQUALANCE_EYE_COLOR, AQUALANCE_EYE_COLOR,
                AQUALANCE_EYE_COLOR, AQUALANCE_EYE_COLOR, AQUALANCE_EYE_COLOR, AQUALANCE_EYE_COLOR,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static class Enums {
        @SpireEnum
        public static AbstractGameAction.AttackEffect WATER;
        @SpireEnum
        public static AbstractGameAction.AttackEffect BLOOD;
        @SpireEnum
        public static DamageInfo.DamageType MAGIC;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static void initialize() {
        BaseMod.subscribe(new AquaLanceMod());

        try {
            Properties defaults = new Properties();
            defaults.put(THEME_SONG_SETTING, Boolean.toString(false));
            defaults.put(EXTENDED_CUT_SETTING, Boolean.toString(true));
            modConfig = new SpireConfig(modID, "Config", defaults);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheAquaLance(TheAquaLance.characterStrings.NAMES[1], THE_AQUALANCE),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, THE_AQUALANCE);
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });

        BaseMod.addRelicToCustomPool(new RuneOfIce(), TheAquaLance.Enums.AQUALANCE_TURQUOISE_COLOR);
        BaseMod.addRelicToCustomPool(new RuneOfLivingWater(), TheAquaLance.Enums.AQUALANCE_TURQUOISE_COLOR);
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SecondMagicNumber());
        BaseMod.addDynamicVariable(new SecondDamage());
        new AutoAdd(modID)
                .packageFilter(AbstractEasyCard.class)
                .setDefaultSeen(true)
                .cards();
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/eng/Cardstrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/eng/Relicstrings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/eng/Charstrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/eng/Powerstrings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, modID + "Resources/localization/eng/Powerstrings.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, modID + "Resources/localization/eng/UIstrings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, modID + "Resources/localization/eng/Potionstrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio(THEME_KEY, THEME_OGG);
        BaseMod.addAudio(STAB_KEY, STAB_OGG);
        BaseMod.addAudio(MULTI_STAB_KEY, MULTI_STAB_OGG);
        BaseMod.addAudio(SPLASH_KEY, SPLASH_OGG);
        BaseMod.addAudio(WATERFALL_KEY, WATERFALL_OGG);
        BaseMod.addAudio(FOOTSTEPS_KEY, FOOTSTEPS_OGG);
        BaseMod.addAudio(TRUCK_KEY, TRUCK_OGG);
        BaseMod.addAudio(FIRE_KEY, FIRE_OGG);
    }

    @Override
    public void receivePostInitialize() {
        BaseMod.addPotion(HobblePotion.class, Color.GREEN.cpy(), null, Color.YELLOW.cpy(), HobblePotion.POTION_ID, THE_AQUALANCE);
        BaseMod.addPotion(IntPotion.class, Color.YELLOW.cpy(), null, null, IntPotion.POTION_ID, THE_AQUALANCE);
        BaseMod.addPotion(InsightPotion.class, Color.WHITE.cpy(), null, null, InsightPotion.POTION_ID, THE_AQUALANCE);
        BaseMod.addPotion(WaterBucket.class, new Color(0,206/256F,209/256F, 1), null, null, WaterBucket.POTION_ID, THE_AQUALANCE);
        BaseMod.addPotion(SharkFinPotion.class, Color.LIGHT_GRAY.cpy(), null, null, SharkFinPotion.POTION_ID, THE_AQUALANCE);

        ModPanel settingsPanel = new ModPanel();

        float currentYposition = 740f;
        float spacingY = 55f;

        ModLabeledToggleButton themeSongButton = new ModLabeledToggleButton(CardCrawlGame.languagePack.getUIString(
                makeID(THEME_SONG_UISTRING)).TEXT[0], 350.0f, currentYposition, Settings.CREAM_COLOR,
                FontHelper.charDescFont, isThemeSong(), settingsPanel, label -> {},
                button -> {
                    if (modConfig != null) {
                        modConfig.setBool(THEME_SONG_SETTING, button.enabled);
                        saveConfig();
                    }
                });

        currentYposition -= spacingY;
        ModLabeledToggleButton extendedCutButton = new ModLabeledToggleButton(CardCrawlGame.languagePack.getUIString(
                makeID(EXTENDED_CUT_UISTRING)).TEXT[0], 350.0f, currentYposition, Settings.CREAM_COLOR,
                FontHelper.charDescFont, isExtendedCut(), settingsPanel, label -> {},
                button -> {
                    if (modConfig != null) {
                        modConfig.setBool(EXTENDED_CUT_SETTING, button.enabled);
                        saveConfig();
                    }
                });

        settingsPanel.addUIElement(themeSongButton);
        settingsPanel.addUIElement(extendedCutButton);

        logger.info("Load Badge Image and make settings panel");
        Texture badgeTexture = new Texture(BADGE_IMG);
        BaseMod.registerModBadge(badgeTexture, REGISTRATION_STRINGS[0], REGISTRATION_STRINGS[1], REGISTRATION_STRINGS[2],
                settingsPanel);

        logger.info("Done loading badge Image");
        logger.info("Done loading badge Image and mod options");
    }

    public void receivePostDungeonInitialize()
    {
        if (AbstractDungeon.player.chosenClass == THE_AQUALANCE) {
            AbstractDungeon.shopRelicPool.removeIf(r -> r.equals(StrangeSpoon.ID));
            AbstractDungeon.rareRelicPool.removeIf(r -> r.equals(DeadBranch.ID));
            AbstractDungeon.rareRelicPool.removeIf(r -> r.equals(UnceasingTop.ID));
            AbstractDungeon.rareRelicPool.removeIf(r -> r.equals(ChemicalX.ID));
        }
    }

    private void saveConfig() {
        try {
            modConfig.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isExtendedCut() {
        if (modConfig == null) {
            return false;
        }
        return modConfig.getBool(EXTENDED_CUT_SETTING);
    }

    public static boolean isThemeSong() {
        if (modConfig == null) {
            return false;
        }
        return modConfig.getBool(THEME_SONG_SETTING);
    }
}
