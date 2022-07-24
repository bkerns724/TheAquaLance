package theExile.cards;

import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class Divination extends AbstractExileCard {
    public final static String ID = makeID(Divination.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 2;
    private final static int CYCLE_AMOUNT = 1;
    private final static int COST = 1;

    public Divination() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new ScryAction(magicNumber));
        cardDraw(CYCLE_AMOUNT);
        discard(CYCLE_AMOUNT);
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}