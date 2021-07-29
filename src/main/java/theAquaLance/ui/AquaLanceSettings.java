package theAquaLance.ui;

import basemod.ModButton;
import basemod.ModLabel;
import basemod.ModPanel;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Prefs;
import theAquaLance.AquaLanceMod;

import static theAquaLance.TheAquaLance.Enums.THE_AQUALANCE;

public class AquaLanceSettings {
    private static final String UI_ID = AquaLanceMod.makeID("ModSettingsPanel");

    public static ModPanel createSettingsPanel() {
        String[] ModSettingsText = (CardCrawlGame.languagePack.getUIString(UI_ID).TEXT);

        ModPanel settingsPanel = new ModPanel();

        ModLabel messageLabel = new ModLabel("", 400.0F, 220.0F, Color.GREEN, FontHelper.tipHeaderFont, settingsPanel, label -> { });
        settingsPanel.addUIElement(messageLabel);

        ModButton unlockA20Button = new ModButton(370.0F, 470.0F, settingsPanel, b -> {
            unlockA20(THE_AQUALANCE);
            messageLabel.text = ModSettingsText[1];
        });
        settingsPanel.addUIElement(unlockA20Button);

        ModLabel unlockA20Label = new ModLabel(ModSettingsText[0], 500.0F, 525.0F, Color.WHITE, FontHelper.tipHeaderFont, settingsPanel, label -> { });
        settingsPanel.addUIElement(unlockA20Label);

        return settingsPanel;
    }

    private static void unlockA20(AbstractPlayer.PlayerClass clz) {
        Prefs prefs = CardCrawlGame.characterManager.getCharacter(clz).getPrefs();
        if (prefs.getInteger("WIN_COUNT", 0) == 0) {
            prefs.putInteger("WIN_COUNT", 1);
        }
        prefs.putInteger("LAST_ASCENSION_LEVEL", 20);
        prefs.putInteger("ASCENSION_LEVEL", 20);
        prefs.flush();
    }
}