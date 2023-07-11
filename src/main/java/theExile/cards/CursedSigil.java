package theExile.cards;

import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.forAllMonstersLiving;

public class CursedSigil extends AbstractExileCard {
    public final static String ID = makeID(CursedSigil.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = -2;

    public CursedSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        magicNumber = baseMagicNumber = MAGIC;
        sigil = true;
    }

    @Override
    public void nonTargetUse() {
        forAllMonstersLiving(m -> applyToEnemy(m, new JinxPower(m, magicNumber)));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}