package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.TriggerEnemyMarkAction;
import theAquaLance.powers.MyMarkPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Pressure extends AbstractEasyCard {
    public final static String ID = makeID("Pressure");
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public Pressure() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = getDebuffCount(m);
        if (count > 0)
            applyToEnemy(m, new MyMarkPower(m, count*magicNumber));
        atb(new TriggerEnemyMarkAction(m));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}