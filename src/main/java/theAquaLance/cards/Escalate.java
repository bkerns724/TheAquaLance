package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.AbstractCardPatch;
import theAquaLance.powers.EscalatePower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Escalate extends AbstractEasyCard {
    public final static String ID = makeID("Escalate");
    private final static int MAGIC = 1;
    private final static int COST = 2;

    public Escalate() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EscalatePower(adp(), magicNumber));
    }

    public void upp() {
        uDesc();
        AbstractCardPatch.AbstractCardField.replenish.set(this, true);
    }
}