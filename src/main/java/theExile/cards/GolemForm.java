package theExile.cards;

import com.megacrit.cardcrawl.powers.MetallicizePower;
import theExile.powers.GolemPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class GolemForm extends AbstractExileCard {
    public final static String ID = makeID(GolemForm.class.getSimpleName());
    private final static int MAGIC = 6;
    private final static int UPGRADE_MAGIC = 2;
    public final static int SECOND_MAGIC = 20;
    public final static int UPGRADE_SECOND = 6;
    private final static int COST = 3;

    public GolemForm() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void nonTargetUse() {
        applyToSelf(new MetallicizePower(adp(), magicNumber));
        applyToSelf(new GolemPower(secondMagic));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
        upMagic2(UPGRADE_SECOND);
    }
}