package theArcanist.cards;

import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.atb;
import static theArcanist.util.Wiz.discard;

public class WellPrepared extends AbstractArcanistCard {
    public final static String ID = makeID(WellPrepared.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 3;
    private final static int COST = 0;
    private final static int DISCARD_AMOUNT = 1;

    public WellPrepared() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        secondMagic = baseSecondMagic = SECOND_MAGIC;
        ExhaustiveVariable.setBaseValue(this, secondMagic);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        discard(DISCARD_AMOUNT);
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}