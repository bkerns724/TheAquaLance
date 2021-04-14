package theAquaLance;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import basemod.interfaces.PostDungeonInitializeSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.relics.DeadBranch;
import com.megacrit.cardcrawl.relics.StrangeSpoon;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import theAquaLance.cards.*;
import theAquaLance.relics.RuneOfIce;

import java.util.ArrayList;

import static theAquaLance.TheAquaLance.Enums.TURQUOISE_COLOR;
import static theAquaLance.AquaLanceMod.*;

public class TheAquaLance extends CustomPlayer implements PostDungeonInitializeSubscriber {
    private static final String[] orbTextures = {
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
    static final String ID = makeID("TheAquaLance");
    static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    static final String[] NAMES = characterStrings.NAMES;
    static final String[] TEXT = characterStrings.TEXT;
    private static final int NUM_STRIKES = 4;
    private static final int NUM_DEFENDS = 4;
    private static final String STARTER_CARD1 = Jab.ID;
    private static final String STARTER_CARD2 = Waterfall.ID;
    private static final String STARTER_RELIC = RuneOfIce.ID;
    private static final int STARTING_HP = 80;

    public TheAquaLance(String name, PlayerClass setClass) {
        super(name, setClass, new CustomEnergyOrb(orbTextures, modID + "Resources/images/char/mainChar/orb/vfx.png", null), new SpriterAnimation(
                modID + "Resources/images/char/mainChar/static.scml"));
        initializeClass(null,
                SHOULDER1,
                SHOULDER2,
                CORPSE,
                getLoadout(), 20.0F, -10.0F, 166.0F, 327.0F, new EnergyManager(3));


        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 240.0F * Settings.scale);
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
            retVal.add(Strike.ID);
        for (int i = 0; i < NUM_DEFENDS; i++)
            retVal.add(Defend.ID);
        retVal.add(STARTER_CARD1);
        retVal.add(STARTER_CARD2);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(STARTER_RELIC);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("UNLOCK_PING", MathUtils.random(-0.2F, 0.2F));
        // CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, false);
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
        return TURQUOISE_COLOR;
    }

    @Override
    public Color getCardTrailColor() {
        return AQUALANCE_EYE_COLOR.cpy();
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
        return new Waterfall();
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheAquaLance(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return AQUALANCE_EYE_COLOR.cpy();
    }

    @Override
    public Color getSlashAttackColor() {
        return AQUALANCE_EYE_COLOR.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL,
                AbstractGameAction.AttackEffect.POISON,
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL};
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_AQUALANCE;
        @SpireEnum(name = "TURQUOISE_COLOR")
        public static AbstractCard.CardColor TURQUOISE_COLOR;
        @SpireEnum(name = "TURQUOISE_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_AQUALANCE_COLOR;
    }

    public void receivePostDungeonInitialize()
    {
        AbstractDungeon.shopRelicPool.removeIf(r -> r.equals(ChemicalX.ID));
        AbstractDungeon.shopRelicPool.removeIf(r -> r.equals(StrangeSpoon.ID));
        AbstractDungeon.rareRelicPool.removeIf(r -> r.equals(DeadBranch.ID));
    }
}
