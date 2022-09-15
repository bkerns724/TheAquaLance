package theExile.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.util.Wiz;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class SnowfallPower extends AbstractExilePower implements PowerWithButton {
    public static String POWER_ID = makeID(SnowfallPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final String TEXTURE_STRING = "exilemodResources/images/ui/SnowfallButton.png";
    private boolean ready;

    public SnowfallPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        ready = true;
        this.name = NAME;
    }

    @Override
    public void atStartOfTurn() {
        ready = true;
    }

    @Override
    public Texture getTexture() {
        return ImageMaster.loadImage(TEXTURE_STRING);
    }

    @Override
    public boolean isButtonDisabled() {
        return !ready;
    }

    @Override
    public void onButtonPress() {
        if (ready) {
            int frostAmount = amount;
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    if (!adp().hand.isEmpty()) {
                        forAllMonstersLiving(mon -> Wiz.applyToEnemyTop(mon, new FrostbitePower(mon, frostAmount)));
                        att(new DiscardAction(adp(), adp(), 1, false));
                    }
                }
            });
            ready = false;
        }
    }

    @Override
    public ArrayList<PowerTip> getHoverTips() {
        ArrayList<PowerTip> list = new ArrayList<>();
        list.add(new PowerTip(name, DESCRIPTIONS[2].replace("!A!", Integer.toString(amount))));
        return list;
    }
}