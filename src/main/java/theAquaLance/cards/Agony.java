package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Agony extends AbstractEasyCard {
    public final static String ID = makeID("Agony");
    private final static int UPGRADED_COST = 1;
    private final static int COST = 2;

    public Agony() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = getShardCount(m);
        if (count > 0) {
            applyToEnemy(m, new StrengthPower(m, -count));
            applyToEnemy(m, new VulnerablePower(m, count, false));
        }
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}