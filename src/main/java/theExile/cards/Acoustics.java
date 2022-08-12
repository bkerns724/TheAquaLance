package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.AcousticsPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Acoustics extends AbstractExileCard {
    public final static String ID = makeID(Acoustics.class.getSimpleName());
    private final static int UPGRADED_COST = 1;
    private final static int COST = 2;

    public Acoustics() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void applyAttributes() {
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new AcousticsPower());
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}