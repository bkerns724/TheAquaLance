package theExile.cards;

import theExile.powers.CorrodedPower;
import theExile.powers.TempNegStrengthPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class Pitfalls extends AbstractExileCard {
    public final static String ID = makeID(Pitfalls.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public Pitfalls() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void nonTargetUse() {
        Wiz.forAllMonstersLiving(mon -> {
            Wiz.applyToEnemy(mon, new CorrodedPower(mon, magicNumber));
            Wiz.applyToEnemy(mon, new TempNegStrengthPower(mon, magicNumber));
        });
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}