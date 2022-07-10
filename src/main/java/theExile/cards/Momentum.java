package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.MomentumPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class Momentum extends AbstractExileCard {
    public final static String ID = makeID(Momentum.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int UPGRADED_COST = 0;
    private final static int COST = 1;

    public Momentum() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MomentumPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}