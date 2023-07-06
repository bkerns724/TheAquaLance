package theExile.cards;

import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theExile.powers.HastePower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class Haste extends AbstractExileCard {
    public final static String ID = makeID(Haste.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = -1;
    private final static int COST = 1;

    public Haste() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        Wiz.applyToSelf(new StrengthPower(adp(), -magicNumber));
        Wiz.applyToSelf(new DexterityPower(adp(), -magicNumber));
        Wiz.applyToSelf(new HastePower(1));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}