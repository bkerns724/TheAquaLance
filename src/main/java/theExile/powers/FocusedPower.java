package theExile.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class FocusedPower extends AbstractExilePower implements PowerWithButton {
    public static String POWER_ID = makeID(FocusedPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final String TEXTURE_STRING = "exilemodResources/images/ui/FocusButton.png";
    int counter = 0;

    public FocusedPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
        counter = amount;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        counter += stackAmount;
    }

    @Override
    public void atStartOfTurn() {
        counter = amount;
    }

    @Override
    public Texture getTexture() {
        return ImageMaster.loadImage(TEXTURE_STRING);
    }

    @Override
    public boolean isButtonDisabled() {
        return counter > 0;
    }

    @Override
    public void onButtonPress() {
        if (counter > 0) {
            atb(new PutOnDeckAction(adp(), adp(), 1, false));
            counter--;
        }
    }

    @Override
    public int getNumber() {
        if (counter > 0)
            return counter;
        else
            return -1;
    }

    @Override
    public ArrayList<PowerTip> getHoverTips() {
        ArrayList<PowerTip> list = new ArrayList<>();
        list.add(new PowerTip(name, description));
        return list;
    }
}