package theExile.cards;

import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class SpiralSigil extends AbstractExileCard {
    public final static String ID = makeID(SpiralSigil.class.getSimpleName());
    private final static int COST = -2;

    public SpiralSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        exhaust = true;
        sigil = true;
    }

    @Override
    public void nonTargetUse() {
        forAllMonstersLiving(mon -> {
            int count = getDebuffCount(mon);
            if (count > 0)
                applyToEnemy(mon, new JinxPower(mon, count));
        });
    }

    public void upp() {
        exhaust = false;
    }
}