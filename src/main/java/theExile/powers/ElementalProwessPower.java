package theExile.powers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class ElementalProwessPower extends AbstractExilePower {
    public static String POWER_ID = makeID(ElementalProwessPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ElementalProwessPower(int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, adp(), amount);
        this.name = NAME;
    }
}