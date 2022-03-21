package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.cards.AbstractArcanistCard;
import theArcanist.powers.SummonMonsterPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class SummonMonster extends AbstractArcanistCard {
    public final static String ID = makeID("SummonMonster");
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 5;
    private final static int COST = 2;

    public SummonMonster() {
        super(ID, COST, CardType.POWER, ArcanistMod.Enums.UNIQUE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SummonMonsterPower(adp(), 1, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}