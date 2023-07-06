package theExile.cards;

import theExile.powers.BurningPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.forAllMonstersLiving;

public class BurningSigil extends AbstractExileCard {
    public final static String ID = makeID(BurningSigil.class.getSimpleName());
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = -2;

    public BurningSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        sigil = true;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        forAllMonstersLiving(m -> applyToEnemy(m, new BurningPower(m, magicNumber)));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}