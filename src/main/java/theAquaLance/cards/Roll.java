package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.DrawNextTurnPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Roll extends AbstractEasyCard {
    public final static String ID = makeID("Roll");
    private final static int MAGIC = 2;
    private final static int SECOND_MAGIC = 1;
    private final static int UPGRADE_SECOND = 1;
    private final static int COST = 1;

    public Roll() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        applyToSelf(new DrawNextTurnPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeSecondMagic(UPGRADE_SECOND);
        uDesc();
    }
}