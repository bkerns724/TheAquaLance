package theExile.cards;

import theExile.powers.ToxicSpellsPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class ToxicSpells extends AbstractExileCard {
    public final static String ID = makeID(ToxicSpells.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public ToxicSpells() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void nonTargetUse() {
        applyToSelf(new ToxicSpellsPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}