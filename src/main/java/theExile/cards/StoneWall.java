package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.MyAddTempHPAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class StoneWall extends AbstractExileCard {
    public final static String ID = makeID(StoneWall.class.getSimpleName());
    private final static int MAGIC = 7;
    private final static int UPGRADE_MAGIC = 3;
    private final static int COST = 1;

    public StoneWall() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new MyAddTempHPAction(p, p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}