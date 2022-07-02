package theArcanist;

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
import com.evacipated.cardcrawl.mod.stslib.icons.CustomIconHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.IncreaseMaxHpAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.neow.NeowReward;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theArcanist.cards.AbstractArcanistCard;
import theArcanist.cards.cardvars.SecondMagicNumber;
import theArcanist.events.*;
import theArcanist.icons.*;
import theArcanist.potions.*;
import theArcanist.relics.AbstractArcanistRelic;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static theArcanist.TheArcanist.Enums.THE_ARCANIST;
import static theArcanist.util.Wiz.atb;
import static theArcanist.util.Wiz.forAllMonstersLiving;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class ArcanistMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        AddAudioSubscriber,
        OnStartBattleSubscriber,
        PostUpdateSubscriber,
        PostInitializeSubscriber {
    public static final String SETTINGS_FILE = "ArcanistModSettings";

    public static final String modID = "arcanistmod";
    public static final String RESOURCES_PRE = "arcanistmodResources/";

    public static Logger logger = LogManager.getLogger(ArcanistMod.class.getName());

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static final String EXTENDED_CUT_SETTING = "ExtendedCutSetting";
    public static final String BANNER_SETTING = "BannerSetting";
    public static final String EVENT_SETTING = "EventSetting";
    public static final String SETTINGS_STRINGS = "Settings";

    private static SpireConfig modConfig = null;

    public static final String SHOULDER1 = RESOURCES_PRE + "images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = RESOURCES_PRE + "images/char/mainChar/shoulder2.png";
    public static final String CORPSE = RESOURCES_PRE + "images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = RESOURCES_PRE + "images/512/attack.png";
    private static final String SKILL_S_ART = RESOURCES_PRE + "images/512/skill.png";
    private static final String POWER_S_ART = RESOURCES_PRE + "images/512/power.png";
    private static final String CARD_ENERGY_S = RESOURCES_PRE + "images/512/energy.png";
    private static final String TEXT_ENERGY = RESOURCES_PRE + "images/512/text_energy.png";
    private static final String ATTACK_L_ART = RESOURCES_PRE + "images/1024/attack.png";
    private static final String SKILL_L_ART = RESOURCES_PRE + "images/1024/skill.png";
    private static final String POWER_L_ART = RESOURCES_PRE + "images/1024/power.png";
    private static final String CARD_ENERGY_L = RESOURCES_PRE + "images/1024/energy.png";
    private static final String CHARSELECT_BUTTON = RESOURCES_PRE + "images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = RESOURCES_PRE + "images/charSelect/charBG.png";

    public static final String WATER_EFFECT_FILE = RESOURCES_PRE + "images/vfx/Water.png";
    public static final String BLOOD_EFFECT_FILE = RESOURCES_PRE + "images/vfx/Blood.png";
    public static final String ACID_EFFECT_FILE = RESOURCES_PRE + "images/vfx/Acid.png";
    public static final String ICE_EFFECT_FILE = RESOURCES_PRE + "images/vfx/Ice.png";
    public static final String ICE_M_EFFECT_FILE = RESOURCES_PRE + "images/vfx/Ice_M.png";
    public static final String SOUL_FIRE_EFFECT_FILE = RESOURCES_PRE + "images/vfx/SoulFire.png";
    public static final String FORCE_EFFECT_FILE = RESOURCES_PRE + "images/vfx/Force.png";
    public static final String FORCE_M_EFFECT_FILE = RESOURCES_PRE + "images/vfx/Force_M.png";
    public static final String FORCE_L_EFFECT_FILE = RESOURCES_PRE + "images/vfx/Force_L.png";
    public static final String DARK_EFFECT_FILE = RESOURCES_PRE + "images/vfx/Dark.png";
    public static final String DARK_M_EFFECT_FILE = RESOURCES_PRE + "images/vfx/Dark_M.png";
    public static final String DARK_L_EFFECT_FILE = RESOURCES_PRE + "images/vfx/Dark_L.png";
    public static final String DARK_L2_EFFECT_FILE = RESOURCES_PRE + "images/vfx/Dark_L2.png";
    public static final String RESONANT_EFFECT_FILE = RESOURCES_PRE + "images/vfx/Resonant.png";
    public static final String RESONANT_M_EFFECT_FILE = RESOURCES_PRE + "images/vfx/Resonant_M.png";
    public static final String RESONANT_L_EFFECT_FILE = RESOURCES_PRE + "images/vfx/Resonant_L.png";

    public static final String COLD_KEY = makeID("Cold");
    private static final String COLD_OGG = RESOURCES_PRE + "audio/Cold.ogg";
    public static final String COLD_M_KEY = makeID("Cold_M");
    private static final String COLD_M_OGG = RESOURCES_PRE + "audio/Cold_M.ogg";
    public static final String PEW_KEY = makeID("Pew");
    private static final String PEW_OGG = RESOURCES_PRE + "audio/Pew.ogg";

    private static final String BADGE_IMG = RESOURCES_PRE + "images/Badge.png";
    private static final String[] REGISTRATION_STRINGS = {
            "The Arcanist", "Bryan", "This mod adds a new character, the Arcanist."
    };

    public static float time = 0.0f;
    public static final Color purpleColor = new Color(0.45f, 0f, 0.6f, 1.0f);
    public static final Color darkColor = new Color(0.25f, 0.25f, 0.25f, 1.0f);
    public static final Color ARCANIST_EYE_COLOR = purpleColor.cpy();

    public ArcanistMod() {
        BaseMod.addColor(TheArcanist.Enums.ARCANIST_BLARPLE_COLOR, ARCANIST_EYE_COLOR, ARCANIST_EYE_COLOR, ARCANIST_EYE_COLOR,
                ARCANIST_EYE_COLOR, ARCANIST_EYE_COLOR, ARCANIST_EYE_COLOR, ARCANIST_EYE_COLOR,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return RESOURCES_PRE + "" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return RESOURCES_PRE + "images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return RESOURCES_PRE + "images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return RESOURCES_PRE + "images/powers/" + resourcePath;
    }

    public static class Enums {
        @SpireEnum
        public static AbstractGameAction.AttackEffect WATER;
        @SpireEnum
        public static AbstractGameAction.AttackEffect BLOOD;
        @SpireEnum
        public static AbstractGameAction.AttackEffect ACID;
        @SpireEnum
        public static AbstractGameAction.AttackEffect FORCE;
        @SpireEnum
        public static AbstractGameAction.AttackEffect FORCE_M;
        @SpireEnum
        public static AbstractGameAction.AttackEffect FORCE_L;
        @SpireEnum
        public static AbstractGameAction.AttackEffect ICE;
        @SpireEnum
        public static AbstractGameAction.AttackEffect ICE_M;
        @SpireEnum
        public static AbstractGameAction.AttackEffect ICE_L;
        @SpireEnum
        public static AbstractGameAction.AttackEffect SOUL_FIRE;
        @SpireEnum
        public static AbstractGameAction.AttackEffect SOUL_FIRE_M;
        @SpireEnum
        public static AbstractGameAction.AttackEffect SOUL_FIRE_L;
        @SpireEnum
        public static AbstractGameAction.AttackEffect DARK;
        @SpireEnum
        public static AbstractGameAction.AttackEffect DARK_M;
        @SpireEnum
        public static AbstractGameAction.AttackEffect DARK_L;
        @SpireEnum
        public static AbstractGameAction.AttackEffect DARK_WAVE;
        @SpireEnum
        public static AbstractGameAction.AttackEffect DARK_WAVE_M;
        @SpireEnum
        public static AbstractGameAction.AttackEffect DARK_WAVE_L;
        @SpireEnum
        public static AbstractGameAction.AttackEffect RESONANT;
        @SpireEnum
        public static AbstractGameAction.AttackEffect RESONANT_M;
        @SpireEnum
        public static AbstractGameAction.AttackEffect RESONANT_L;
        @SpireEnum
        public static AbstractGameAction.AttackEffect BLUNT_MASSIVE;
        @SpireEnum
        public static AbstractGameAction.AttackEffect SLASH_MASSIVE;
        @SpireEnum
        public static AbstractCard.CardRarity UNIQUE;
        @SpireEnum
        public static NeowReward.NeowRewardType UNIQUE_CARD_REWARD;
        @SpireEnum
        public static AbstractPotion.PotionRarity EVENT;
    }

    public static String makeCardPath(String resourcePath) {
        return RESOURCES_PRE + "images/cards/" + resourcePath;
    }

    public static void initialize() {
        BaseMod.subscribe(new ArcanistMod());

        try {
            Properties defaults = new Properties();
            defaults.put(EXTENDED_CUT_SETTING, Boolean.toString(true));
            defaults.put(BANNER_SETTING, Boolean.toString(false));
            defaults.put(EVENT_SETTING, Boolean.toString(false));
            modConfig = new SpireConfig(modID, "Config", defaults);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheArcanist(TheArcanist.characterStrings.NAMES[1], THE_ARCANIST),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, THE_ARCANIST);
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractArcanistRelic.class)
                .any(AbstractArcanistRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SecondMagicNumber());

        CustomIconHelper.addCustomIcon(Force.get());
        CustomIconHelper.addCustomIcon(Ice.get());
        CustomIconHelper.addCustomIcon(Dark.get());
        CustomIconHelper.addCustomIcon(Scourge.get());
        CustomIconHelper.addCustomIcon(SoulFire.get());

        new AutoAdd(modID)
                .packageFilter(AbstractArcanistCard.class)
                .setDefaultSeen(true)
                .cards();
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, RESOURCES_PRE + "localization/eng/Cardstrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, RESOURCES_PRE + "localization/eng/Relicstrings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, RESOURCES_PRE + "localization/eng/Charstrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, RESOURCES_PRE + "localization/eng/Powerstrings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, RESOURCES_PRE + "localization/eng/Potionstrings.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, RESOURCES_PRE + "localization/eng/UIstrings.json");
        BaseMod.loadCustomStringsFile(OrbStrings.class, RESOURCES_PRE + "localization/eng/Orbstrings.json");
        BaseMod.loadCustomStringsFile(EventStrings.class, RESOURCES_PRE + "localization/eng/Eventstrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(RESOURCES_PRE + "localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receivePostInitialize() {
        setupSettings();
        addPotions();
        addEvents();
    }

    private void setupSettings () {
        ModPanel settingsPanel = new ModPanel();

        float currentYposition = 740f;

        ModLabeledToggleButton extendedCutButton = new ModLabeledToggleButton(CardCrawlGame.languagePack.getUIString(
                makeID(SETTINGS_STRINGS)).TEXT[0], 350.0f, currentYposition, Settings.CREAM_COLOR,
                FontHelper.charDescFont, isExtendedCut(), settingsPanel, label -> {},
                button -> {
                    if (modConfig != null) {
                        modConfig.setBool(EXTENDED_CUT_SETTING, button.enabled);
                        saveConfig();
                    }
                });

        settingsPanel.addUIElement(extendedCutButton);
        currentYposition -= 60.0f;

        ModLabeledToggleButton bannerButton = new ModLabeledToggleButton(CardCrawlGame.languagePack.getUIString(
                makeID(SETTINGS_STRINGS)).TEXT[1], 350.0f, currentYposition, Settings.CREAM_COLOR,
                FontHelper.charDescFont, isUniversalBanners(), settingsPanel, label -> {},
                button2 -> {
                    if (modConfig != null) {
                        modConfig.setBool(BANNER_SETTING, button2.enabled);
                        saveConfig();
                    }
                });

        settingsPanel.addUIElement(bannerButton);
        currentYposition -= 60.0f;

        ModLabeledToggleButton eventButton = new ModLabeledToggleButton(CardCrawlGame.languagePack.getUIString(
                makeID(SETTINGS_STRINGS)).TEXT[2], 350.0f, currentYposition, Settings.CREAM_COLOR,
                FontHelper.charDescFont, isUniversalEvents(), settingsPanel, label -> {},
                button3 -> {
                    if (modConfig != null) {
                        modConfig.setBool(EVENT_SETTING, button3.enabled);
                        saveConfig();
                    }
                });

        settingsPanel.addUIElement(eventButton);

        Texture badgeTexture = new Texture(BADGE_IMG);
        BaseMod.registerModBadge(badgeTexture, REGISTRATION_STRINGS[0], REGISTRATION_STRINGS[1], REGISTRATION_STRINGS[2],
                settingsPanel);
    }

    private void saveConfig() {
        try {
            modConfig.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isExtendedCut() {
        if (modConfig == null)
            return false;
        return modConfig.getBool(EXTENDED_CUT_SETTING);
    }

    public static boolean isUniversalBanners() {
        if (modConfig == null)
            return false;
        return modConfig.getBool(BANNER_SETTING);
    }

    public static boolean isUniversalEvents() {
        if (modConfig == null)
            return false;
        return modConfig.getBool(EVENT_SETTING);
    }

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio(COLD_KEY, COLD_OGG);
        BaseMod.addAudio(COLD_M_KEY, COLD_M_OGG);
        BaseMod.addAudio(PEW_KEY, PEW_OGG);
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom room) {
        if (room.event instanceof FightingPit && room.eliteTrigger)
            forAllMonstersLiving(m -> atb(new IncreaseMaxHpAction(m, FightingPit.HEALTH_BUFF, true)));
        if (room.event instanceof VoidSpirits)
            forAllMonstersLiving(m -> atb(new IncreaseMaxHpAction(m, VoidSpirits.HEALTH_BUFF, true)));
    }

    public static Color getColor() {
        Color blendedColor = new Color();
        blendedColor.r = (float) ((purpleColor.r + darkColor.r)/2.0f + Math.sin(ArcanistMod.time/20) *(purpleColor.r - darkColor.r)/2.0f);
        blendedColor.g = (float) ((purpleColor.g + darkColor.g)/2.0f + Math.sin(ArcanistMod.time/20) *(purpleColor.g - darkColor.g)/2.0f);
        blendedColor.b = (float) ((purpleColor.b + darkColor.b)/2.0f + Math.sin(ArcanistMod.time/20) *(purpleColor.b - darkColor.b)/2.0f);
        blendedColor.a = 1.0f;
        return blendedColor;
    }

    @Override
    public void receivePostUpdate() {
        time += Gdx.graphics.getDeltaTime();
    }

    private static void addEvents() {
        BaseMod.addEvent(MarketActOne.getParams());
        BaseMod.addEvent(FightingPit.getParams());
        BaseMod.addEvent(ClericsRequest.getParams());
        BaseMod.addEvent(MarketActTwo.getParams());
        BaseMod.addEvent(StrangeGarden.getParams());
        BaseMod.addEvent(MysteriousHoard.getParams());
        BaseMod.addEvent(VoidSpirits.getParams());
        BaseMod.addEvent(SpellForge.getParams());
        BaseMod.addEvent(ResearchCenter.getParams());
        BaseMod.addEvent(LadyInRed.getParams());
        BaseMod.addEvent(SpellShootingRange.getParams());
    }

    private static void addPotions() {
        BaseMod.addPotion(CursedBrew.class, Color.PURPLE.cpy(), Color.BLACK.cpy(), null, CursedBrew.POTION_ID, THE_ARCANIST);
        BaseMod.addPotion(MurkyElixir.class, Color.DARK_GRAY.cpy(), Color.BLACK.cpy(), null, MurkyElixir.POTION_ID, THE_ARCANIST);
        BaseMod.addPotion(SigilPotion.class, Color.GOLD.cpy(), Color.GOLDENROD.cpy(), null, SigilPotion.POTION_ID, THE_ARCANIST);
        BaseMod.addPotion(StoneskinPotion.class, Color.BROWN.cpy(), null, null, StoneskinPotion.POTION_ID, THE_ARCANIST);

        BaseMod.addPotion(LiquidCalamity.class, Color.BLUE.cpy(), Color.PINK.cpy(), Color.PURPLE.cpy(), LiquidCalamity.POTION_ID, THE_ARCANIST);
        BaseMod.addPotion(ForbiddenFlask.class, Color.PINK.cpy(), Color.MAGENTA.cpy(), null, ForbiddenFlask.POTION_ID, THE_ARCANIST);
        BaseMod.addPotion(WhirlpoolPotion.class, Color.CYAN.cpy(), null, null, WhirlpoolPotion.POTION_ID, THE_ARCANIST);

        BaseMod.addPotion(ElixirOfFalseHealth.class, Color.YELLOW.cpy(), Color.GOLD.cpy(), null, ElixirOfFalseHealth.POTION_ID, THE_ARCANIST);
        BaseMod.addPotion(UnicornBlood.class, Color.WHITE.cpy(), null, null, UnicornBlood.POTION_ID, THE_ARCANIST);

        BaseMod.addPotion(SteelhidePotion.class, Color.LIGHT_GRAY.cpy(), Color.GRAY.cpy(), null, SteelhidePotion.POTION_ID, THE_ARCANIST);
        BaseMod.addPotion(PoisonousSmokeBomb.class, Color.DARK_GRAY.cpy(), null, Color.GREEN, PoisonousSmokeBomb.POTION_ID, THE_ARCANIST);
        BaseMod.addPotion(ToxicOil.class, Color.LIME, null, null, ToxicOil.POTION_ID, THE_ARCANIST);
        BaseMod.addPotion(SlipperyPotion.class, Color.CYAN, null, null, SlipperyPotion.POTION_ID, THE_ARCANIST);
    }
}
