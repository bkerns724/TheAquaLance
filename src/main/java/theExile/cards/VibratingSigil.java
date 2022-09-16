package theExile.cards;

import com.megacrit.cardcrawl.powers.StrengthPower;
import theExile.powers.RingingPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class VibratingSigil extends AbstractExileCard {
    public final static String ID = makeID(VibratingSigil.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 2;
    private final static int SECOND_MAGIC = 1;
    private final static int COST = -2;

    public VibratingSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
        sigil = true;
    }

    @Override
    public void nonTargetUse() {
        Wiz.forAllMonstersLiving(mon -> Wiz.applyToEnemy(mon, new RingingPower(mon, magicNumber)));
        Wiz.forAllMonstersLiving(mon -> Wiz.applyToEnemy(mon, new StrengthPower(mon, -secondMagic)));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}