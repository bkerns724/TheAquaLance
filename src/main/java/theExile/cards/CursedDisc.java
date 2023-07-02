package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.CursedDiscAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class CursedDisc extends AbstractExileCard {
    public final static String ID = makeID(CursedDisc.class.getSimpleName());
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 1;

    public CursedDisc() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        atb(new CursedDiscAction(m, magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}