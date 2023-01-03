package theExile.relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.CardStrings;
import theExile.TheExile;
import theExile.damagemods.EldritchDamage;

import static theExile.ExileMod.makeID;

public class BlueMarbles extends AbstractExileRelic {
    public static final String ID = makeID(BlueMarbles.class.getSimpleName());
    public static final float MULT = 1.5f;
    private static final CardStrings iceStrings = CardCrawlGame.languagePack.getCardStrings(EldritchDamage.ID);

    public BlueMarbles() {
        super(ID, RelicTier.RARE, LandingSound.CLINK, TheExile.Enums.EXILE_BROWN_COLOR);
        setUpdatedDescription();
    }

    @Override
    public void setUpdatedDescription() {
        description = getUpdatedDescription();
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(iceStrings.DESCRIPTION, iceStrings.EXTENDED_DESCRIPTION[0]));
        initializeTips();
    }
}
