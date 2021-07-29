package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.TacticalCombatPlusPower;
import theAquaLance.powers.TacticalCombatPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class TacticalCombat extends AbstractEasyCard {
    public final static String ID = makeID("TacticalCombat");
    private final static int MAGIC = 1;
    private final static int COST = 0;

    public TacticalCombat() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded)
            applyToSelf(new TacticalCombatPower(adp(), magicNumber));
        else
            applyToSelf(new TacticalCombatPlusPower(adp(), magicNumber));
    }

    public void upp() {
        uDesc();
    }
}