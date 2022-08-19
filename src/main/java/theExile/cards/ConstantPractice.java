package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.ConstantPracticeAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class ConstantPractice extends AbstractExileCard {
    public final static String ID = makeID(ConstantPractice.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 3;

    public ConstantPractice() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new ConstantPracticeAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}