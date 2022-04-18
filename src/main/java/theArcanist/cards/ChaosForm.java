package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import com.megacrit.cardcrawl.powers.DrawPower;
import theArcanist.powers.ChaosFormPower;
import theArcanist.relics.DetailedContract;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class ChaosForm extends AbstractArcanistCard {
    public final static String ID = makeID(ChaosForm.class.getSimpleName());
    private final static int MAGIC = 1;
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
            amount += 1;
        applyToSelf(new ChaosFormPower(p, amount));
        if (upgraded) {
            applyToSelf(new DrawPower(adp(), 1));
            applyToSelf(new ConfusionPower(adp()));
        }
    }

    public void upp() {
    }
}