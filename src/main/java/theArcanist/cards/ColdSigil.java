package theArcanist.cards;

import theArcanist.powers.FrostbitePower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class ColdSigil extends AbstractSigilCard {
    public final static String ID = makeID("ColdSigil");
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public ColdSigil() {
        super(ID, CardRarity.UNCOMMON, CardType.SKILL, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void onManualDiscard() {
        forAllMonstersLiving(monster -> {
            int deb = getDebuffCount(monster);
            if (deb > 0)
                applyToEnemy(monster, new FrostbitePower(monster, deb*magicNumber));
        });
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}