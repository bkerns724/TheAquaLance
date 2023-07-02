package theExile.cards;

import theExile.ExileMod;
import theExile.powers.AuraOfMisfortunePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class AuraOfMisfortune extends AbstractExileCard {
    public final static String ID = makeID(AuraOfMisfortune.class.getSimpleName());
    private final static int COST = 1;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public AuraOfMisfortune() {
        super(ID, COST, CardType.POWER, ExileMod.Enums.UNIQUE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new AuraOfMisfortunePower(magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}