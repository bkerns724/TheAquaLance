package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.GatheredPowerAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class UnleashMalice extends AbstractExileCard {
    public final static String ID = makeID(UnleashMalice.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int COST = -1;

    public UnleashMalice() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void singleTargetUse(AbstractMonster m) {
        atb(new GatheredPowerAction(m, upgraded, freeToPlayOnce, energyOnUse));
    }

    public void upp() {
    }
}