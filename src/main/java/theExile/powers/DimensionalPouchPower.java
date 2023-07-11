package theExile.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.ExileMod;

import java.util.ArrayList;

import static theExile.util.Wiz.*;

public class DimensionalPouchPower extends AbstractExilePower implements PowerWithButton {
    public static final String POWER_ID = ExileMod.makeID(DimensionalPouchPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final String TEXTURE_STRING = "exilemodResources/images/ui/DrawButton.png";
    private int counter;
    public final static int CARD_DRAW = 3;

    public DimensionalPouchPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        counter = amount;
        amount2 = CARD_DRAW;
        updateDescription();
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
        return counter == 0 || EnergyPanel.totalCount <= 0;
    }

    @Override
    public void onButtonPress() {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (EnergyPanel.totalCount > 0) {
                    adp().loseEnergy(1);
                    att(new DrawCardAction(CARD_DRAW));
                }
                isDone = true;
            }
        });
        counter--;
    }

    @Override
    public int getNumber() {
        if (counter > 0)
            return counter;
        return -1;
    }

    @Override
    public ArrayList<PowerTip> getHoverTips() {
        ArrayList<PowerTip> list = new ArrayList<>();
        list.add(new PowerTip(name, DESCRIPTIONS[2].replace("!A2!", Integer.toString(amount2))));
        return list;
    }
}