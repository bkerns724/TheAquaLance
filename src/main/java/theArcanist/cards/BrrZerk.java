package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.BrrZerkPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class BrrZerk extends AbstractEasyCard {
    public final static String ID = makeID("BrrZerk");
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 2;

    public BrrZerk() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BrrZerkPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}