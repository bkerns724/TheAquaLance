package theArcanist.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.actions.ArcanistGreedAction;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Greed extends AbstractArcanistCard {
    public final static String ID = makeID(Greed.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int COST = 1;

    public Greed() {
        super(ID, COST, CardType.CURSE, CardRarity.SPECIAL, CardTarget.SELF, CardColor.CURSE);
        baseMagicNumber = magicNumber = MAGIC;
        AutoplayField.autoplay.set(this, true);
        exhaust = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new ArcanistGreedAction(magicNumber));
    }

    public void upp() {
    }
}