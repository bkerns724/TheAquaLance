package theExile.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.util.Wiz;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToEnemy;

public class EfficientPower extends AbstractExilePower implements PowerWithButton {
    public static String POWER_ID = makeID(EfficientPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final String TEXTURE_STRING = "exilemodResources/images/ui/EfficientButton.png";
    private boolean ready;

    public EfficientPower(int amount) {
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
            Wiz.discard(1);
            Wiz.forAllMonstersLiving(mon -> applyToEnemy(mon, new RingingPower(mon, amount)));
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