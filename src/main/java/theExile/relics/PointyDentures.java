package theExile.relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.CardStrings;
import theExile.TheExile;
import theExile.damagemods.EldritchDamage;

import static theExile.ExileMod.makeID;

public class PointyDentures extends AbstractExileRelic {
    public static final String ID = makeID(PointyDentures.class.getSimpleName());
    public static final int TEMP_HP_AMOUNT = 2;
    private static final CardStrings eldritchStrings = CardCrawlGame.languagePack.getCardStrings(EldritchDamage.ID);

    // code in EldritchDamage
    public PointyDentures() {
        super(ID, RelicTier.UNCOMMON,
                LandingSound.HEAVY,
                TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = TEMP_HP_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void setUpdatedDescription() {
        description = getUpdatedDescription();
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(eldritchStrings.DESCRIPTION, eldritchStrings.EXTENDED_DESCRIPTION[0]));
        tips.add(new PowerTip(eldritchStrings.EXTENDED_DESCRIPTION[1], eldritchStrings.EXTENDED_DESCRIPTION[2]));
        initializeTips();
    }
}
