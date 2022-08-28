package theExile.cards;

import theExile.powers.StasisFieldPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.forAllMonstersLiving;

public class StasisField extends AbstractExileCard {
    public final static String ID = makeID(StasisField.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public StasisField() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void nonTargetUse() {
        forAllMonstersLiving(mo -> applyToEnemy(mo, new StasisFieldPower(mo, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}