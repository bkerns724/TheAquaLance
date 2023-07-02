package theExile;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.evacipated.cardcrawl.mod.stslib.icons.CustomIconHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import theExile.cards.*;
import theExile.patches.CutsceneMultiScreenPatch;
import theExile.relics.NecklaceOfShielding;
import theExile.vfx.ExileVictoryEffect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static theExile.ExileMod.*;
import static theExile.TheExile.Enums.EXILE_BROWN_COLOR;

public class TheExile extends CustomPlayer {
    private static final String[] ORB_TEXTURES = {
            modID + "Resources/images/char/mainChar/orb/layer1.png",
            modID + "Resources/images/char/mainChar/orb/layer2.png",
            modID + "Resources/images/char/mainChar/orb/layer3.png",
            modID + "Resources/images/char/mainChar/orb/layer4.png",
            modID + "Resources/images/char/mainChar/orb/layer5.png",
            modID + "Resources/images/char/mainChar/orb/layer6.png",
            modID + "Resources/images/char/mainChar/orb/layer1d.png",
            modID + "Resources/images/char/mainChar/orb/layer2d.png",
            modID + "Resources/images/char/mainChar/orb/layer3d.png",
            modID + "Resources/images/char/mainChar/orb/layer4d.png",
            modID + "Resources/images/char/mainChar/orb/layer5d.png",};

    private static final String CUTSCENE_FOLDER = modID + "Resources/images/cutScene/";
    private static final String CUTSCENE_BG = "blueBg.jpg";
    private static final String[] CUTSCENE_PANELS = {
            "panelOne.png",
            "panelTwo.png",
            "panelThree.png",
            "panelFour.png",
            "panelFive.png",
            "panelSix.png",
            "panelSeven.png",
            "panelEight.png",
            "panelNine.png",
            "panelTen.png",
            "panelEleven.png",
            "panelTwelve.png"};

    private static final String SKELETON_ATLAS = modID + "Resources/images/char/mainChar/idle/skeleton.atlas";
    private static final String SKELETON_JSON = modID + "Resources/images/char/mainChar/idle/skeleton.json";

    static final String ID = makeID("TheExile");
    static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    static final String[] NAMES = characterStrings.NAMES;
    static final String[] TEXT = characterStrings.TEXT;
    private static final int NUM_STRIKES = 4;
    private static final int NUM_DEFENDS = 4;
    private static final int STARTING_HP = 70;

    public TheExile(String name, PlayerClass setClass) {
        super(name, setClass,
                new CustomEnergyOrb(ORB_TEXTURES, modID + "Resources/images/char/mainChar/orb/vfx.png",
                        null), null, null);

        initializeClass(null,
                SHOULDER1, SHOULDER2, CORPSE,
                getLoadout(), -20.0F, -24.0F, 240.0F, 240.0F, new EnergyManager(3));

        loadAnimation(SKELETON_ATLAS, SKELETON_JSON, 1.0F);
        AnimationState.TrackEntry e = state.setAnimation(0, "Idle", true);
        stateData.setMix("Hit", "Idle", 0.1F);
        e.setTimeScale(0.9F);

        dialogX = drawX + 0.0F * Settings.scale;
        dialogY = drawY + 170.0F * Settings.scale;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, STARTING_HP, 0, 99, 5, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for (int i = 0; i < NUM_STRIKES; i++)
            retVal.add(StaffStrike.ID);
        for (int i = 0; i < NUM_DEFENDS; i++)
            retVal.add(Defend.ID);

        retVal.add(EldritchBolt.ID);
        retVal.add(ColdSigil.ID);

        return retVal;
    }

    @Override
    public String getSensoryStoneText() {
        return TEXT[3];
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(NecklaceOfShielding.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("UNLOCK_PING", MathUtils.random(-0.2F, 0.2F));
        // I HATE SCREEN SHAKE.
        // Even if the other characters do it, mine won't
        // CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, false);
        Collection<AbstractCustomIcon> icons = CustomIconHelper.getAllIcons();
    }

    public void damage(DamageInfo info) {
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output - this.currentBlock > 0) {
            AnimationState.TrackEntry e = state.setAnimation(0, "Hit", false);
            state.addAnimation(0, "Idle", true, 0.0F);
            e.setTimeScale(0.9F);
        }

        super.damage(info);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "UNLOCK_PING";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return Math.floorDiv(STARTING_HP, 10);
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return EXILE_BROWN_COLOR;
    }

    @Override
    public Color getCardTrailColor() {
        return Color.BROWN.cpy();
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        if (AbstractDungeon.cardRng != null) {
            ArrayList<AbstractCard> cardList = new ArrayList<>();
            cardList.add(new AcidSpray());
            cardList.add(new Miasma());
            cardList.add(new SickBurn());
            int x = AbstractDungeon.cardRng.random(0, cardList.size() - 1);
            return cardList.get(x);
        }
        return new Focus();
    }

    public static AbstractCard getCardForLibrary() {
        // Sorry Pasha if this causes problems, but this needs to be seeded
        if (AbstractDungeon.cardRng != null) {
            ArrayList<AbstractCard> cardList = new ArrayList<>();
            cardList.add(new QuickThinking());
            cardList.add(new ClockSigil());
            int x = AbstractDungeon.cardRng.random(0, cardList.size() - 1);
            return cardList.get(x);
        }
        return new Focus();
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheExile(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return Color.BROWN.cpy();
    }

    @Override
    public Color getSlashAttackColor() {
        return Color.BROWN.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                ExileMod.Enums.FORCE,
                ExileMod.Enums.ICE,
                ExileMod.Enums.ELDRITCH,
                ExileMod.Enums.LIGHTNING_S
        };
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    @Override
    public List<CutscenePanel> getCutscenePanels() {
        List<CutscenePanel> panels = new ArrayList<>();
        if (isExtendedCut()) {
            panels.add(new CutscenePanel(CUTSCENE_FOLDER + CUTSCENE_PANELS[0]));
            panels.add(new CutscenePanel(CUTSCENE_FOLDER + CUTSCENE_PANELS[1]));
            panels.add(new CutscenePanel(CUTSCENE_FOLDER + CUTSCENE_PANELS[2]));
            panels.add(new CutscenePanel(CUTSCENE_FOLDER + CUTSCENE_PANELS[3]));
            panels.add(new CutscenePanel(CUTSCENE_FOLDER + CUTSCENE_PANELS[4]));
            panels.add(new CutscenePanel(CUTSCENE_FOLDER + CUTSCENE_PANELS[5]));
            panels.add(new CutscenePanel(CUTSCENE_FOLDER + CUTSCENE_PANELS[6]));
            panels.add(new CutscenePanel(CUTSCENE_FOLDER + CUTSCENE_PANELS[7]));
            panels.add(new CutscenePanel(CUTSCENE_FOLDER + CUTSCENE_PANELS[8]));
            panels.add(new CutscenePanel(CUTSCENE_FOLDER + CUTSCENE_PANELS[9]));
            panels.add(new CutscenePanel(CUTSCENE_FOLDER + CUTSCENE_PANELS[10]));
            panels.add(new CutscenePanel(CUTSCENE_FOLDER + CUTSCENE_PANELS[11]));

            CutsceneMultiScreenPatch.CutscenePanelNewScreenField.startsNewScreen.set(panels.get(3), true);
            CutsceneMultiScreenPatch.CutscenePanelNewScreenField.startsNewScreen.set(panels.get(6), true);
            CutsceneMultiScreenPatch.CutscenePanelNewScreenField.startsNewScreen.set(panels.get(9), true);
        }
        else {
            panels.add(new CutscenePanel(CUTSCENE_FOLDER + CUTSCENE_PANELS[9]));
            panels.add(new CutscenePanel(CUTSCENE_FOLDER + CUTSCENE_PANELS[10]));
            panels.add(new CutscenePanel(CUTSCENE_FOLDER + CUTSCENE_PANELS[11]));
        }

        return panels;
    }

    public Texture getCutsceneBg() {
        return ImageMaster.loadImage(CUTSCENE_FOLDER + CUTSCENE_BG);
    }

    @Override
    public void updateVictoryVfx(ArrayList<AbstractGameEffect> effects) {
        effects.add(new ExileVictoryEffect());
    }

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_EXILE;
        @SpireEnum(name = "EXILE_COLOR")
        public static AbstractCard.CardColor EXILE_BROWN_COLOR;
        @SpireEnum(name = "EXILE_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_EXILE_COLOR;
    }
}
