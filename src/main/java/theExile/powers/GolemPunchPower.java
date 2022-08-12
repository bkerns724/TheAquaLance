package theExile.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.cards.AbstractExileCard;
import theExile.cards.GolemPunchHelper;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class GolemPunchPower extends AbstractExilePower implements PowerWithButton {
    public static String POWER_ID = makeID(GolemPunchPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractExileCard helperCard;
    private static final String TEXTURE_STRING = "exilemodResources/images/ui/GolemPunchButton.png";
    public int counter;

    public GolemPunchPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
        helperCard = new GolemPunchHelper();
        counter = amount;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        counter += amount;
    }

    @Override
    public void atStartOfTurn() {
        counter = amount;
    }

    @Override
    public void onButtonPress() {
        AbstractMonster mon = AbstractDungeon.getMonsters().getRandomMonster(null, true,
                AbstractDungeon.cardRandomRng);
        helperCard.calculateCardDamage(mon);
        helperCard.onUse(adp(), mon);
        counter--;
        GolemFrostPower pow = (GolemFrostPower)adp().getPower(GolemPunchPower.POWER_ID);
        if (pow != null)
            pow.counter--;
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
    public ArrayList<PowerTip> getHoverTips() {
        ArrayList<PowerTip> list = new ArrayList<>();
        list.add(new PowerTip(DESCRIPTIONS[2], DESCRIPTIONS[3]));
        return list;
    }
}