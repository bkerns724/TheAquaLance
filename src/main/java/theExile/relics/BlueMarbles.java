package theExile.relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.CardStrings;
import theExile.TheExile;
import theExile.damagemods.PhantasmalDamage;
import theExile.damagemods.IceDamage;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

// Code in Ice and Phantasmal damage mods
public class BlueMarbles extends AbstractExileRelic {
    public static final String ID = makeID(BlueMarbles.class.getSimpleName());
    private static final CardStrings forceStrings = CardCrawlGame.languagePack.getCardStrings(PhantasmalDamage.ID);
    private static final CardStrings iceStrings = CardCrawlGame.languagePack.getCardStrings(IceDamage.ID);
    public static final float INCREASE = 1.5f;

    public BlueMarbles() {
        super(ID, RelicTier.RARE, LandingSound.CLINK, TheExile.Enums.EXILE_BROWN_COLOR);
        setUpdatedDescription();
    }

    @Override
    public void setUpdatedDescription() {
        description = getUpdatedDescription();
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(forceStrings.DESCRIPTION, forceStrings.EXTENDED_DESCRIPTION[0]));
        tips.add(new PowerTip(iceStrings.DESCRIPTION, iceStrings.EXTENDED_DESCRIPTION[0]));
        initializeTips();
    }

    @Override
    public boolean canSpawn() {
        return !adp().hasRelic(ManaPurifier.ID);
    }
}
