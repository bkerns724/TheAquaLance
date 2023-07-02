package theExile.relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.CardStrings;
import theExile.TheExile;
import theExile.damagemods.DeathLightningDamage;

import static theExile.ExileMod.makeID;

public class M10Core extends AbstractExileRelic {
    public static final String ID = makeID(M10Core.class.getSimpleName());
    private static final CardStrings lightningStrings = CardCrawlGame.languagePack.getCardStrings(DeathLightningDamage.ID);
    public static final int BONUS_DAMAGE = 2;

    public M10Core() {
        super(ID, RelicTier.RARE, LandingSound.CLINK, TheExile.Enums.EXILE_BROWN_COLOR);
        amount = BONUS_DAMAGE;
        setUpdatedDescription();
    }

    @Override
    public void setUpdatedDescription() {
        description = getUpdatedDescription();
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(lightningStrings.DESCRIPTION, lightningStrings.EXTENDED_DESCRIPTION[0]));
        initializeTips();
    }
}
