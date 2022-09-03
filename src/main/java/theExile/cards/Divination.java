package theExile.cards;

import theExile.actions.DivinationAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class Divination extends AbstractExileCard {
    public final static String ID = makeID(Divination.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public Divination() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        atb(new DivinationAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}