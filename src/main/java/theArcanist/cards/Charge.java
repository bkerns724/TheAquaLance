package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.ChargePower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Charge extends AbstractArcanistCard {
    public final static String ID = makeID(Charge.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
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