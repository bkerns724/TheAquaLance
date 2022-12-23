package theExile.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.CardStrings;
import theExile.TheExile;
import theExile.damagemods.DeathLightningDamage;
import theExile.powers.ChargePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class M10Core extends AbstractExileRelic {
    public static final String ID = makeID(M10Core.class.getSimpleName());
    private static final CardStrings lightningStrings = CardCrawlGame.languagePack.getCardStrings(DeathLightningDamage.ID);
    public static final int CHARGE_AMOUNT = 3;

    public M10Core() {
        super(ID, RelicTier.RARE, LandingSound.CLINK, TheExile.Enums.EXILE_BROWN_COLOR);
        amount = CHARGE_AMOUNT;
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

    @Override
    public void atBattleStart() {
        flash();
        applyToSelf(new ChargePower(CHARGE_AMOUNT));
        atb(new RelicAboveCreatureAction(adp(), this));
    }
}
