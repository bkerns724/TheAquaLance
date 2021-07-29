package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.DiscardNextNextTurnPower;
import theAquaLance.powers.DiscardNextTurnPower;
import theAquaLance.powers.SoakedPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Jettison extends AbstractEasyCard {
    public final static String ID = makeID("Jettison");
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;

    public Jettison() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // Discard 1 card, discard 1 next turn, if upgraded discard 1 card a turn after that.
        atb(new DiscardAction(p, p, 1, false));
        applyToSelf(new DiscardNextTurnPower(adp(), 1));
        if (upgraded)
            applyToSelf(new DiscardNextNextTurnPower(adp(), 1));
        applyToEnemy(m, new SoakedPower(m, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        uDesc();
    }
}