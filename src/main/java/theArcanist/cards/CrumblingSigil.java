package theArcanist.cards;

import theArcanist.powers.DecayingPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class CrumblingSigil extends AbstractSigilCard {
    public final static String ID = makeID("CrumblingSigil");
    private final static int MAGIC = 7;
    private final static int UPGRADE_MAGIC = 2;

    public CrumblingSigil() {
        super(ID, CardRarity.RARE, CardType.SKILL, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void onManualDiscard() {
        forAllMonstersLiving(monster -> applyToEnemy(monster, new DecayingPower(monster, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}