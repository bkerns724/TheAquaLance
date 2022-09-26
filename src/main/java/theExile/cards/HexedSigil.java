package theExile.cards;

import theExile.powers.JinxPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.forAllMonstersLiving;

public class HexedSigil extends AbstractExileCard {
    public final static String ID = makeID(HexedSigil.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = -2;

    public HexedSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    @Override
    public void nonTargetUse() {
        forAllMonstersLiving(mon -> Wiz.applyToEnemy(mon, new JinxPower(mon, magicNumber)));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}