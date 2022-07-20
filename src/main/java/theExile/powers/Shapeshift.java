package theExile.powers;

import com.megacrit.cardcrawl.cards.curses.Injury;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theExile.cards.AbstractExileCard;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class Shapeshift extends AbstractExileCard {
    public final static String ID = makeID(Shapeshift.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public Shapeshift() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        cardsToPreview = new Injury();
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(adp(), magicNumber));
        applyToSelf(new DexterityPower(adp(), magicNumber));
    }

    @Override
    public void onPickup() {
        new ShowCardAndObtainEffect(new Injury(), Settings.WIDTH * 0.35f, Settings.HEIGHT * 0.5f);
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}