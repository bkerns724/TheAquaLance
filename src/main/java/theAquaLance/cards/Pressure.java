package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.TriggerPressureAction;
import theAquaLance.powers.PressurePower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Pressure extends AbstractEasyCard {
    public final static String ID = makeID("Pressure");
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;

    public Pressure() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = getDebuffCount(m);
        if (count > 0)
            applyToEnemy(m, new PressurePower(m, count*magicNumber));
        atb(new TriggerPressureAction(m));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}