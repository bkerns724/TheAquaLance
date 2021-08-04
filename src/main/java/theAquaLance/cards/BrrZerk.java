package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.AbstractCardPatch;
import theAquaLance.powers.BrrZerkPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class BrrZerk extends AbstractEasyCard {
    public final static String ID = makeID("BrrZerk");
    private final static int MAGIC = 1;
    private final static int COST = 0;

    public BrrZerk() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BrrZerkPower(adp(), magicNumber));
    }

    public void upp() {
        uDesc();
        AbstractCardPatch.AbstractCardField.replenish.set(this, true);
    }
}