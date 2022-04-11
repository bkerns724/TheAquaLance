package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.ChaosFormPower;
import theArcanist.relics.DetailedContract;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class ChaosForm extends AbstractArcanistCard {
    public final static String ID = makeID(ChaosForm.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 3;

    public ChaosForm() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        int amount = magicNumber;
        if (adp().hasRelic(DetailedContract.ID))
            amount += 3;
        applyToSelf(new ChaosFormPower(p, amount));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}