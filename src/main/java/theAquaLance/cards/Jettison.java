package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theAquaLance.powers.DiscardNextNextTurnPower;
import theAquaLance.powers.DiscardNextTurnPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Jettison extends AbstractEasyCard {
    public final static String ID = makeID("Jettison");
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public Jettison() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // Draw 1 card, draw 1 next turn, if upgraded draw 1 card a turn after that.
        atb(new DiscardAction(p, p, 1, false));
        applyToSelf(new DiscardNextTurnPower(adp(), 1));
        if (upgraded)
            applyToSelf(new DiscardNextNextTurnPower(adp(), 1));
        applyToSelf(new VigorPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        uDesc();
    }
}