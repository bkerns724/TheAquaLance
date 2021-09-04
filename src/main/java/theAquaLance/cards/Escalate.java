package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.EscalatePower;
import theAquaLance.powers.IntelligencePower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Escalate extends AbstractEasyCard {
    public final static String ID = makeID("Escalate");
    private final static int MAGIC = 1;
    private final static int COST = 2;
    private final static int SECOND_MAGIC = 2;

    public Escalate() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EscalatePower(adp(), magicNumber));
        if (upgraded)
            applyToSelf(new IntelligencePower(adp(), secondMagic));
    }

    public void upp() {
        uDesc();
    }
}