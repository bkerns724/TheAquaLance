package theExile.cards;

import theExile.powers.CorrodedPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.forAllMonstersLiving;

public class AcidicSigil extends AbstractExileCard {
    public final static String ID = makeID(AcidicSigil.class.getSimpleName());
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = -2;

    public AcidicSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    @Override
    public void nonTargetUse() {
        forAllMonstersLiving(m -> applyToEnemy(m, new CorrodedPower(m, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}