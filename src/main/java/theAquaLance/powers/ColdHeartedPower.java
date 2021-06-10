package theAquaLance.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.AquaLanceMod;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theAquaLance.util.Wiz.*;

public class ColdHeartedPower extends AbstractEasyPower {
    public static final String POWER_ID = AquaLanceMod.makeID("ColdHearted");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final int DEBUFFS_REQUIRED = 3;

    public int amount2;

    public ColdHeartedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
        amount2 = 0;
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.type == PowerType.DEBUFF)
            amount2++;
        if (amount2 == DEBUFFS_REQUIRED) {
            amount2 = 0;
            applyToSelf(new HastePower(owner, amount));
        }
    }

    @Override
    public void atStartOfTurn() {
        amount2 = 0;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + DEBUFFS_REQUIRED + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        super.renderAmount(sb, x, y, c);

        c = Color.WHITE.cpy();
        FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2),
                x, y + 15.0F * Settings.scale, fontScale, c);
    }
}