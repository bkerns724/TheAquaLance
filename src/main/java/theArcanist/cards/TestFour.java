package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class TestFour extends AbstractArcanistCard {
    public final static String ID = makeID("TestFour");
    private final static int COST = 0;

    public TestFour() {
        super(ID, COST, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
    }
}