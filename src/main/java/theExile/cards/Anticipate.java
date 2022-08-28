package theExile.cards;

import theExile.powers.AnticipatePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Anticipate extends AbstractExileCard {
    public final static String ID = makeID(Anticipate.class.getSimpleName());
    private final static int COST = 0;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;

    public Anticipate() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void nonTargetUse() {
        applyToSelf(new AnticipatePower(magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}