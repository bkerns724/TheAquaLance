package theExile.relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.CardStrings;
import theExile.TheExile;
import theExile.damagemods.ForceDamage;

import static theExile.ExileMod.makeID;

public class Bestiary extends AbstractExileRelic {
    public static final String ID = makeID(Bestiary.class.getSimpleName());
    public static final float BONUS = 1f;
    private static final CardStrings forceStrings = CardCrawlGame.languagePack.getCardStrings(ForceDamage.ID);

    public Bestiary() {
        super(ID, RelicTier.RARE, LandingSound.HEAVY, TheExile.Enums.EXILE_BROWN_COLOR);
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
