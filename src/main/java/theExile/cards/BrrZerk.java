package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.BrrZerkPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class BrrZerk extends AbstractExileCard {
    public final static String ID = makeID(BrrZerk.class.getSimpleName());
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 1;

    public BrrZerk() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BrrZerkPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}