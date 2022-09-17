package theExile.cards;

import theExile.powers.DecayPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.forAllMonstersLiving;

public class CrumblingSigil extends AbstractExileCard {
    public final static String ID = makeID(CrumblingSigil.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = -2;

    public CrumblingSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    public void nonTargetUse() {
        forAllMonstersLiving(mon -> applyToEnemy(mon, new DecayPower(mon, magicNumber)));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}