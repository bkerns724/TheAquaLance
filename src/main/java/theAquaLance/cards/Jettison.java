package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.DiscardNextNextTurnPower;
import theAquaLance.powers.DiscardNextTurnPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Jettison extends AbstractEasyCard {
    public final static String ID = makeID("Jettison");
    private final static int BLOCK = 3;
    private final static int UPGRADE_BLOCK = 1;

    public Jettison() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        // Draw 1 card, draw 1 next turn, if upgraded draw 1 card a turn after that.
        atb(new DiscardAction(p, p, 1, false));
        applyToSelf(new DiscardNextTurnPower(adp(), 1));
        if (upgraded)
            applyToSelf(new DiscardNextNextTurnPower(adp(), 1));

    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}