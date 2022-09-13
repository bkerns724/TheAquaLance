package theExile.relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.CardStrings;
import theExile.TheExile;
import theExile.damagemods.PhantasmalDamage;

import static theExile.ExileMod.makeID;

public class ManaCrystal extends AbstractExileRelic {
    public static final String ID = makeID(ManaCrystal.class.getSimpleName());
    private static final CardStrings forceStrings = CardCrawlGame.languagePack.getCardStrings(PhantasmalDamage.ID);
    public static final int NEW_LIMIT = 3;

    public ManaCrystal() {
        super(ID, RelicTier.RARE, LandingSound.CLINK, TheExile.Enums.EXILE_BROWN_COLOR);
        amount = NEW_LIMIT;
        setUpdatedDescription();
    }

    @Override
    public void setUpdatedDescription() {
        description = getUpdatedDescription();
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(forceStrings.DESCRIPTION, forceStrings.EXTENDED_DESCRIPTION[0]));
        initializeTips();
    }
}
