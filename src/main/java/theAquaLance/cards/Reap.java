package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import theAquaLance.actions.TriggerEnemyMarkAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Reap extends AbstractEasyCard {
    public final static String ID = makeID("Reap");
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public Reap() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            atb(new TriggerEnemyMarkAction(m));
        atb(new RemoveSpecificPowerAction(m, adp(), MarkPower.POWER_ID));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}