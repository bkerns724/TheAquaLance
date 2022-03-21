package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.ChaosAuraPower;
import theArcanist.relics.DetailedContract;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class ChaosAura extends AbstractArcanistCard {
    public final static String ID = makeID(ChaosAura.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public ChaosAura() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        int amount = magicNumber;
        if (adp().hasRelic(DetailedContract.ID))
            amount += 3;
        applyToSelf(new ChaosAuraPower(p, amount));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}