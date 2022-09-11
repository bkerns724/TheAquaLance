package theExile.cards;

import theExile.powers.ElementalProwessPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class ElementalProwess extends AbstractExileCard {
    public final static String ID = makeID(ElementalProwess.class.getSimpleName());
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 2;

    public ElementalProwess() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        Wiz.applyToSelf(new ElementalProwessPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}