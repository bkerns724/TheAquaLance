package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.OverExtendPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Roll extends AbstractEasyCard {
    public final static String ID = makeID("Roll");
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int OVER_EXTEND = 3;
    private final static int COST = 0;

    public Roll() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        applyToSelf(new OverExtendPower(adp(), OVER_EXTEND));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}