package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.FeintPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Feint extends AbstractEasyCard {
    public final static String ID = makeID("Feint");
    private final static int MAGIC = 1;
    private final static int COST = 1;
    private final static int UPGRADE_COST = 0;

    public Feint() {
        super(ID, COST, CardType.SKILL, CardRarity.BASIC, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new FeintPower(m, magicNumber));
        atb(new DiscardAction(adp(), adp(), magicNumber, false));
    }

    public void upp() {
        upgradeBaseCost(UPGRADE_COST);
    }
}