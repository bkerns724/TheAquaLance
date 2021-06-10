package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Rush extends AbstractEasyCard {
    public final static String ID = makeID("Rush");
    private final static int BLOCK = 3;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public Rush() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        atb(new DiscardAction(adp(), adp(), magicNumber, false));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}