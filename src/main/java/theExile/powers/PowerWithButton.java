package theExile.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.PowerTip;

import java.util.ArrayList;


public interface PowerWithButton {
    Texture getTexture();

    void onButtonPress();

    boolean isButtonDisabled();

    ArrayList<PowerTip> getHoverTips();

    default int getNumber() {return -1;}
}
