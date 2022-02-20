package theArcanist.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.ShadowcloakPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class RipplingShadows extends AbstractArcanistCard {
    public final static String ID = makeID("RipplingShadows");
    private final static int MAGIC = 1;
    private final static int COST = 1;
    private final static int SECOND_MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int DISCARD_AMOUNT = 1;

    // skill, common, self
    public RipplingShadows() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ShadowcloakPower(p, secondMagic));
        atb(new DrawCardAction(magicNumber));
        atb(new DiscardAction(p, p, DISCARD_AMOUNT, false));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}