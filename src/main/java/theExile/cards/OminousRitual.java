package theExile.cards;

import theExile.powers.OminousRitualPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class OminousRitual extends AbstractExileCard {
    public final static String ID = makeID(OminousRitual.class.getSimpleName());
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 1;

    public OminousRitual() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void nonTargetUse() {
        Wiz.forAllMonstersLiving(m -> Wiz.applyToEnemy(m, new OminousRitualPower(m, magicNumber)));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}