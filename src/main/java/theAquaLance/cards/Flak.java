package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.AbstractCardPatch;
import theAquaLance.powers.FlakPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Flak extends AbstractEasyCard {
    public final static String ID = makeID("Flak");
    private final static int MAGIC = 1;
    private final static int COST = 0;

    public Flak() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new FlakPower(adp(), magicNumber));
    }

    public void upp() {
        uDesc();
        AbstractCardPatch.AbstractCardField.replenish.set(this, true);
    }
}