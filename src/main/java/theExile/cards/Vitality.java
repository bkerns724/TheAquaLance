package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.MyAddTempHPAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;
import static theExile.util.Wiz.discard;

public class Vitality extends AbstractExileCard {
    public final static String ID = makeID(Vitality.class.getSimpleName());
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 0;
    private final static int DISCARD_AMOUNT = 1;

    public Vitality() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new MyAddTempHPAction(p, p, magicNumber));
        discard(DISCARD_AMOUNT);
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}