package theExile.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.orbs.SwarmOfBees;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class SummonBeesPower extends AbstractExilePower implements PowerWithButton {
    public static String POWER_ID = makeID(SummonBeesPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final String TEXTURE_STRING = "exilemodResources/images/ui/BeeButton.png";

    public SummonBeesPower() {
        super(POWER_ID, PowerType.BUFF, false, adp(), 1);
        this.name = NAME;
    }

    @Override
    public void stackPower(int stackAmount) {
        amount = 1;
    }

    @Override
    public Texture getTexture() {
        return ImageMaster.loadImage(TEXTURE_STRING);
    }

    @Override
    public boolean isButtonDisabled() {
        return EnergyPanel.totalCount <= 0;
    }

    @Override
    public void onButtonPress() {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (EnergyPanel.totalCount > 0) {
                    adp().loseEnergy(1);
                    att(new ChannelAction(new SwarmOfBees(SwarmOfBees.STING_AMOUNT)));
                }
            isDone = true;
            }
        });
    }

    @Override
    public void updateDescription() {
        description = descriptionArray[0];
        description = description.replace("!A!", "#b" + SwarmOfBees.STING_AMOUNT);
    }

    @Override
    public ArrayList<PowerTip> getHoverTips() {
        ArrayList<PowerTip> list = new ArrayList<>();
        list.add(new PowerTip(name, DESCRIPTIONS[2].replace("!A!", Integer.toString(SwarmOfBees.STING_AMOUNT))));
        return list;
    }
}