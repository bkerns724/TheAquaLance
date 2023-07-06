package theExile.cards;

import theExile.powers.AlteredSpellsPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class AlteredSpells extends AbstractExileCard {
    public final static String ID = makeID(AlteredSpells.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public AlteredSpells() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void nonTargetUse() {
        applyToSelf(new AlteredSpellsPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}