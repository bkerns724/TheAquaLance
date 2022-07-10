package theExile.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;
import static theExile.util.Wiz.discard;

public class Run extends AbstractExileCard {
    public final static String ID = makeID(Run.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 3;

    public Run() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        discard(magicNumber);
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}