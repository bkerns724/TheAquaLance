package theExile.cards;

import theExile.powers.MetamagicPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class Metamagic extends AbstractExileCard {
    public final static String ID = makeID(Metamagic.class.getSimpleName());
    private final static int COST = 0;

    public Metamagic() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
    }

    public void applyAttributes() {
    }

    @Override
    public void nonTargetUse() {
        Wiz.applyToSelf(new MetamagicPower(1));
    }

    public void upp() {
        exhaust = false;
    }
}