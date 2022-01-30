package theArcanist.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Dash extends AbstractArcanistCard {
    public final static String ID = makeID("Dash");
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 3;

    public Dash() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        atb(new DiscardAction(adp(), adp(), magicNumber, false));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}