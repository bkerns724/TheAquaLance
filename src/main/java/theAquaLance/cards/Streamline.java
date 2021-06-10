package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.DrawNextNextTurnPower;
import theAquaLance.powers.DrawNextTurnPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Streamline extends AbstractEasyCard {
    public final static String ID = makeID("Streamline");
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;

    public Streamline() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // Draw 1 card, draw 1 next turn, if upgraded draw 1 card a turn after that.
        atb(new DrawCardAction(1));
        applyToSelf(new DrawNextTurnPower(adp(), 1));
        if (upgraded)
            applyToSelf(new DrawNextNextTurnPower(adp(), 1));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}