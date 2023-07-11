package theExile.cards;

import theExile.powers.MetamagicPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class Metamagic extends AbstractExileCard {
    public final static String ID = makeID(Metamagic.class.getSimpleName());
    private final static int COST = 1;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;

    public Metamagic() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        Wiz.applyToSelf(new MetamagicPower(magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}