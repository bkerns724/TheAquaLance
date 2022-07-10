package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.ChargePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class Charge extends AbstractExileCard {
    public final static String ID = makeID(Charge.class.getSimpleName());
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 1;

    public Charge() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ChargePower(p, magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}