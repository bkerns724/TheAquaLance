package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.RapidsPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Rapids extends AbstractEasyCard {
    public final static String ID = makeID("Rapids");
    private final static int COST = 0;
    private final static int MAGIC = 1;

    public Rapids() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new RapidsPower(adp(), magicNumber));
    }

    public void upp() {
        exhaust = false;
        uDesc();
    }
}