package theExile.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import theExile.powers.ManaBurnPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class ManaBurn extends AbstractExileCard {
    public final static String ID = makeID(ManaBurn.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 0;

    public ManaBurn() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    @Override
    public void nonTargetUse() {
        atb(new GainEnergyAction(magicNumber));
        Wiz.applyToSelf(new ManaBurnPower(1));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}