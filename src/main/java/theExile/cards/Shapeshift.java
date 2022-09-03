package theExile.cards;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class Shapeshift extends AbstractExileCard {
    public final static String ID = makeID(Shapeshift.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 2;
    private final static int COST = 1;

    public Shapeshift() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
        cardsToPreview = new Arthritis();
    }

    public void nonTargetUse() {
        applyToSelf(new StrengthPower(adp(), magicNumber));
        applyToSelf(new DexterityPower(adp(), secondMagic));
    }

    @Override
    public void onPickup() {
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Arthritis(),
                (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}