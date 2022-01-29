package theArcanist;

import IconsAddon.util.CustomIconHelper;
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
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theArcanist.cards.AbstractEasyCard;
import theArcanist.cards.cardvars.SecondMagicNumber;
import theArcanist.cards.damageMods.DarkIcon;
import theArcanist.cards.damageMods.ForceIcon;
import theArcanist.cards.damageMods.IceIcon;
import theArcanist.potions.*;
import theArcanist.relics.AbstractEasyRelic;
import theArcanist.relics.UnmeltingIce;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static theArcanist.TheArcanist.Enums.THE_ARCANIST;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class ArcanistMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        AddAudioSubscriber,
        PostInitializeSubscriber {

    public static final String SETTINGS_FILE = "ArcanistModSettings";

    public static final String modID = "arcanistmod";
    public static final String RESOURCES_PRE = "arcanistmodResources/";

    public static Logger logger = LogManager.getLogger(ArcanistMod.class.getName());

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static final String EXTENDED_CUT_SETTING = "ExtendedCutSetting";
    public static final String EXTENDED_CUT_UISTRING = "ExtendedCutConfig";

    private static SpireConfig modConfig = null;

    public static final Color ARCANIST_EYE_COLOR = CardHelper.getColor(64, 224, 208);

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
    public static final String SOUL_FIRE_EFFECT_FILE = RESOURCES_PRE + "images/vfx/SoulFire.png";
    public static final String PHANTOM_FIST_EFFECT_FILE = RESOURCES_PRE + "images/vfx/PhantomFist.png";
    public static final String DARK_COIL_EFFECT_FILE = RESOURCES_PRE + "images/vfx/DarkCoil.png";
    public static final String LIGHT_COIL_EFFECT_FILE = RESOURCES_PRE + "images/vfx/LightCoil.png";

    public static final String COLD_KEY = makeID("Cold");
    private static final String COLD_OGG = RESOURCES_PRE + "audio/Cold.ogg";

    private static final String BADGE_IMG = RESOURCES_PRE + "images/Badge.png";
    private static final String[] REGISTRATION_STRINGS = {
            "The Arcanist", "Bryan", "This mod adds a new character, the Arcanist."
    };

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
        public static AbstractGameAction.AttackEffect FIST;
        @SpireEnum
        public static AbstractGameAction.AttackEffect ICE;
        @SpireEnum
        public static AbstractGameAction.AttackEffect SOUL_FIRE;
        @SpireEnum
        public static AbstractGameAction.AttackEffect DARK_COIL;
    }

    public static String makeCardPath(String resourcePath) {
        return RESOURCES_PRE + "images/cards/" + resourcePath;
    }

    public static void initialize() {
        BaseMod.subscribe(new ArcanistMod());

        try {
            Properties defaults = new Properties();
            defaults.put(EXTENDED_CUT_SETTING, Boolean.toString(true));
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

        BaseMod.addRelicToCustomPool(new UnmeltingIce(), TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SecondMagicNumber());
        new AutoAdd(modID)
                .packageFilter(AbstractEasyCard.class)
                .setDefaultSeen(true)
                .cards();
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, RESOURCES_PRE + "localization/eng/Cardstrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, RESOURCES_PRE + "localization/eng/Relicstrings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, RESOURCES_PRE + "localization/eng/Charstrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, RESOURCES_PRE + "localization/eng/Powerstrings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, RESOURCES_PRE + "localization/eng/Powerstrings.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, RESOURCES_PRE + "localization/eng/UIstrings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, RESOURCES_PRE + "localization/eng/Potionstrings.json");
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
        CustomIconHelper.addCustomIcon(ForceIcon.get());
        CustomIconHelper.addCustomIcon(IceIcon.get());
        CustomIconHelper.addCustomIcon(DarkIcon.get());

        BaseMod.addPotion(IntPotion.class, Color.YELLOW.cpy(), null, null, IntPotion.POTION_ID, THE_ARCANIST);

        ModPanel settingsPanel = new ModPanel();

        float currentYposition = 740f;

        logger.info(makeID(EXTENDED_CUT_UISTRING));
        logger.info(CardCrawlGame.languagePack.getUIString(makeID(EXTENDED_CUT_UISTRING)));
        logger.info(CardCrawlGame.languagePack.getUIString(makeID(EXTENDED_CUT_UISTRING)).TEXT[0]);

        ModLabeledToggleButton extendedCutButton = new ModLabeledToggleButton(CardCrawlGame.languagePack.getUIString(
                makeID(EXTENDED_CUT_UISTRING)).TEXT[0], 350.0f, currentYposition, Settings.CREAM_COLOR,
                FontHelper.charDescFont, isExtendedCut(), settingsPanel, label -> {},
                button -> {
                    if (modConfig != null) {
                        modConfig.setBool(EXTENDED_CUT_SETTING, button.enabled);
                        saveConfig();
                    }
                });

        settingsPanel.addUIElement(extendedCutButton);

        logger.info("Load Badge Image and make settings panel");
        Texture badgeTexture = new Texture(BADGE_IMG);
        BaseMod.registerModBadge(badgeTexture, REGISTRATION_STRINGS[0], REGISTRATION_STRINGS[1], REGISTRATION_STRINGS[2],
                settingsPanel);

        logger.info("Done loading badge Image");
        logger.info("Done loading badge Image and mod options");
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

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio(COLD_KEY, COLD_OGG);
    }
}
