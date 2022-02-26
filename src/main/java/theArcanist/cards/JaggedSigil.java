package theArcanist.cards;

import theArcanist.powers.ExplosiveSigilPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class JaggedSigil extends AbstractSigilCard {
    public final static String ID = makeID("JaggedSigil");
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;

    public JaggedSigil() {
        super(ID, CardRarity.UNCOMMON, CardType.SKILL, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void onManualDiscard() {
        applyToSelf(new ExplosiveSigilPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}