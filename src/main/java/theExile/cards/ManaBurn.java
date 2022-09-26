package theExile.cards;

import theExile.powers.ManaBurnPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class ManaBurn extends AbstractExileCard {
    public final static String ID = makeID(ManaBurn.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 1;
    private final static int UPGRADED_COST = 0;

    public ManaBurn() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    @Override
    public void nonTargetUse() {
        Wiz.applyToSelf(new ManaBurnPower(magicNumber));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}