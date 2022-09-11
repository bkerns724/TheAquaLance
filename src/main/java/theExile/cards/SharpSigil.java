package theExile.cards;

import theExile.powers.BarbedPower;
import theExile.powers.FrostbitePower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class SharpSigil extends AbstractExileCard {
    public final static String ID = makeID(SharpSigil.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = -2;

    public SharpSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    @Override
    public void nonTargetUse() {
        Wiz.forAllMonstersLiving(mon -> Wiz.applyToEnemy(mon, new FrostbitePower(mon, magicNumber)));
        Wiz.forAllMonstersLiving(mon -> Wiz.applyToEnemy(mon, new BarbedPower(mon, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}