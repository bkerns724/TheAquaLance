package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.PlayDiscardShardAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class DefensiveCombo extends AbstractEasyCard {
    public final static String ID = makeID("DefensiveCombo");
    private final static int BLOCK = 8;
    private final static int UPGRADE_BLOCK = 3;
    private final static int COST = 1;

    public DefensiveCombo() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new PlayDiscardShardAction(m));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}