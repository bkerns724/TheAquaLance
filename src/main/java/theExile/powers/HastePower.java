package theExile.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.ExileMod;

import java.util.ArrayList;

import static theExile.util.Wiz.*;

public class HastePower extends AbstractExilePower implements PowerWithButton {
    public static final String POWER_ID = ExileMod.makeID(HastePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final String TEXTURE_STRING = "exilemodResources/images/ui/ChaoticMindButton.png";
    private int counter;
    private final static int CARD_DRAW = 3;

    public HastePower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
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
        return counter == 0;
    }

    @Override
    public void onButtonPress() {
        atb(new LoseEnergyAction(1));
        cardDraw(CARD_DRAW);
    }

    @Override
    public ArrayList<PowerTip> getHoverTips() {
        ArrayList<PowerTip> list = new ArrayList<>();
        list.add(new PowerTip(name, DESCRIPTIONS[2]));
        return list;
    }
}