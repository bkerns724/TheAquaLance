package theArcanist.cards;

import theArcanist.powers.CursePower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class CursedSigil extends AbstractSigilCard {
    public final static String ID = makeID("CursedSigil");
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public CursedSigil() {
        super(ID, CardRarity.COMMON, CardType.SKILL, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void onManualDiscard() {
        forAllMonstersLiving(monster -> applyToEnemy(monster, new CursePower(monster, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}