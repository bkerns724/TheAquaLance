package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theArcanist.powers.MiniWrathPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Wrath extends AbstractArcanistCard {
    public final static String ID = makeID(Wrath.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = -2;

    public Wrath() {
        super(ID, COST, CardType.CURSE, CardRarity.SPECIAL, CardTarget.NONE, CardColor.CURSE);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void triggerWhenDrawn() {
        applyToSelf(new VulnerablePower(adp(), magicNumber, false));
        applyToSelf(new MiniWrathPower(adp(), magicNumber));
    }

    public void upp() {
    }
}