package theExile.cards;

import theExile.powers.TempNegStrengthPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.forAllMonstersLiving;

public class WailingSigil extends AbstractExileCard {
    public final static String ID = makeID(WailingSigil.class.getSimpleName());
    private final static int MAGIC = 6;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = -2;

    public WailingSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    public void nonTargetUse() {
        forAllMonstersLiving(mon -> applyToEnemy(mon, new TempNegStrengthPower(mon, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}