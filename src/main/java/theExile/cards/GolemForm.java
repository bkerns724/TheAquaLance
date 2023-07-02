package theExile.cards;

import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class GolemForm extends AbstractExileCard {
    public final static String ID = makeID(GolemForm.class.getSimpleName());
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;
    private final static int SECOND_MAGIC = 2;
    private final static int UPGRADE_SECOND = 1;
    private final static int THIRD_MAGIC = 2;
    private final static int UPGRADE_THIRD = 1;
    private final static int COST = 3;

    public GolemForm() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
        baseThirdMagic = thirdMagic = THIRD_MAGIC;
    }

    public void nonTargetUse() {
        applyToSelf(new MetallicizePower(adp(), magicNumber));
        applyToSelf(new DexterityPower(adp(), secondMagic));
        applyToSelf(new ArtifactPower(adp(), thirdMagic));
        atb(new RemoveDebuffsAction(adp()));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
        upMagic2(UPGRADE_SECOND);
        upMagic3(UPGRADE_THIRD);
    }
}