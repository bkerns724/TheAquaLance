package theExile.cards;

import theExile.powers.HarmonyPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Harmony extends AbstractExileCard {
    public final static String ID = makeID(Harmony.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 1;
    private final static int UPGRADED_COST = 0;

    public Harmony() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void nonTargetUse() {
        applyToSelf(new HarmonyPower(magicNumber));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}