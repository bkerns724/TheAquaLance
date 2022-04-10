package theArcanist.cards;

import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArcanist.ArcanistMod.makeID;

public class VoidCurse extends AbstractArcanistCard {
    public final static String ID = makeID(VoidCurse.class.getSimpleName());
    private final static int COST = -2;

    public VoidCurse() {
        super(ID, COST, CardType.CURSE, CardRarity.SPECIAL, CardTarget.NONE, CardColor.CURSE);
        isEthereal = true;
    }

    public void triggerWhenDrawn() {
        this.addToBot(new LoseEnergyAction(1));
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}