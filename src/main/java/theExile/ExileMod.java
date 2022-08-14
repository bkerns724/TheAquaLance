package theExile;

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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.neow.NeowReward;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.FtueTip;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theExile.cards.AbstractExileCard;
import theExile.cards.cardvars.SecondMagicNumber;
import theExile.cards.cardvars.ThirdMagicNumber;
import theExile.events.ClericsRequest;
import theExile.events.ResearchCenter;
import theExile.events.VoidSpirits;
import theExile.icons.*;
import theExile.potions.*;
import theExile.relics.AbstractExileRelic;
import theExile.util.ClickableForPower;
import theExile.util.ClickyFtue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static theExile.TheExile.Enums.THE_EXILE;
import static theExile.util.Wiz.*;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class ExileMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        AddAudioSubscriber,
        OnStartBattleSubscriber,
        PostBattleSubscriber,
        PostUpdateSubscriber,
        PostInitializeSubscriber {
    public static final String SETTINGS_FILE = "ExileModSettings";

    public static final String modID = "exilemod";
    public static final String RESOURCES_PRE = "exilemodResources/";

    public static Logger logger = LogManager.getLogger(ExileMod.class.getName());

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static final String EXTENDED_CUT_SETTING = "ExtendedCutSetting";
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

    public static final String LIGHTNING_EFFECT_FILE = RESOURCES_PRE + "images/vfx/Lightning.png";
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

    public static final String FTUE_IMG = RESOURCES_PRE + "images/ui/Ftue.png";

    public static final String COLD_KEY = makeID("Cold");
    private static final String COLD_OGG = RESOURCES_PRE + "audio/Cold.ogg";
    public static final String COLD_M_KEY = makeID("Cold_M");
    private static final String COLD_M_OGG = RESOURCES_PRE + "audio/Cold_M.ogg";
    public static final String PEW_KEY = makeID("Pew");
    private static final String PEW_OGG = RESOURCES_PRE + "audio/Pew.ogg";
    public static final String JINX_KEY = makeID("Jinx");
    private static final String JINX_OGG = RESOURCES_PRE + "audio/Jinx.ogg";

    private static final String BADGE_IMG = RESOURCES_PRE + "images/Badge.png";
    private static final String[] REGISTRATION_STRINGS = {
            "The Exile", "Bryan", "This mod adds a new character, the Exile."
    };

    public static float time = 0.0f;
    public static final Color purpleColor = new Color(0.45f, 0f, 0.6f, 1.0f);
    public static final Color darkColor = new Color(0.25f, 0.25f, 0.25f, 1.0f);
    public static final Color EXILE_EYE_COLOR = purpleColor.cpy();

    public ExileMod() {
        BaseMod.addColor(TheExile.Enums.EXILE_BLARPLE_COLOR, EXILE_EYE_COLOR, EXILE_EYE_COLOR, EXILE_EYE_COLOR,
                EXILE_EYE_COLOR, EXILE_EYE_COLOR, EXILE_EYE_COLOR, EXILE_EYE_COLOR,
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
        public static AbstractGameAction.AttackEffect LIGHTNING_M;
        @SpireEnum
        public static AbstractGameAction.AttackEffect LIGHTNING_L;
        @SpireEnum
        public static AbstractCard.CardRarity UNIQUE;
        @SpireEnum
        public static NeowReward.NeowRewardType UNIQUE_CARD_REWARD;
        @SpireEnum
        public static AbstractPotion.PotionRarity EVENT;
        @SpireEnum
        public static FtueTip.TipType CLICKY_IMAGE;
        @SpireEnum
        public static AbstractCard.CardTarget AUTOAIM_ENEMY;
    }

    public static String makeCardPath(String resourcePath) {
        return RESOURCES_PRE + "images/cards/" + resourcePath;
    }

    public static void initialize() {
        BaseMod.subscribe(new ExileMod());

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
        BaseMod.addCharacter(new TheExile(TheExile.characterStrings.NAMES[1], THE_EXILE),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, THE_EXILE);
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractExileRelic.class)
                .any(AbstractExileRelic.class, (info, relic) -> {
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
        BaseMod.addDynamicVariable(new ThirdMagicNumber());

        CustomIconHelper.addCustomIcon(Force.get());
        CustomIconHelper.addCustomIcon(Ice.get());
        CustomIconHelper.addCustomIcon(Eldritch.get());
        CustomIconHelper.addCustomIcon(SoulFire.get());
        CustomIconHelper.addCustomIcon(Lightning.get());

        new AutoAdd(modID)
                .packageFilter(AbstractExileCard.class)
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

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio(COLD_KEY, COLD_OGG);
        BaseMod.addAudio(COLD_M_KEY, COLD_M_OGG);
        BaseMod.addAudio(PEW_KEY, PEW_OGG);
        BaseMod.addAudio(JINX_KEY, JINX_OGG);
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom room) {
        if (adp().chosenClass == THE_EXILE && AbstractDungeon.floorNum == 1)
            AbstractDungeon.ftue = new ClickyFtue("whee", "boop", Settings.WIDTH/2f, Settings.HEIGHT/2f);
        if (room.event instanceof VoidSpirits)
            forAllMonstersLiving(m -> atb(new IncreaseMaxHpAction(m, VoidSpirits.HEALTH_BUFF, true)));
    }

    @Override
    public void receivePostUpdate() {
        time += Gdx.graphics.getDeltaTime();
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        ClickableForPower.getClickableList().clear();
    }

    private static void addEvents() {
        BaseMod.addEvent(ClericsRequest.getParams());
        BaseMod.addEvent(VoidSpirits.getParams());
        BaseMod.addEvent(ResearchCenter.getParams());
    }

    private static void addPotions() {
        BaseMod.addPotion(CursedBrew.class, Color.PURPLE.cpy(), Color.BLACK.cpy(), null, CursedBrew.POTION_ID, THE_EXILE);
        BaseMod.addPotion(StoneskinPotion.class, Color.BROWN.cpy(), null, null, StoneskinPotion.POTION_ID, THE_EXILE);
        BaseMod.addPotion(ElixirOfFalseHealth.class, Color.YELLOW.cpy(), Color.GOLD.cpy(), null, ElixirOfFalseHealth.POTION_ID, THE_EXILE);
        BaseMod.addPotion(UnicornBlood.class, Color.WHITE.cpy(), null, null, UnicornBlood.POTION_ID, THE_EXILE);
        BaseMod.addPotion(VampiricPoison.class, Color.LIME, null, null, VampiricPoison.POTION_ID, THE_EXILE);
        BaseMod.addPotion(SteelhidePotion.class, Color.GRAY.cpy(), null, null, SteelhidePotion.POTION_ID, THE_EXILE);
    }
}
