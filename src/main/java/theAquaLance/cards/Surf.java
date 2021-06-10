package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.powers.SoakedPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Surf extends AbstractEasyCard {
    public final static String ID = makeID("Surf");
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int MAGIC_TWO = 1;

    public Surf() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = MAGIC_TWO;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new SoakedPower(m, magicNumber), AquaLanceMod.Enums.WATER);
        atb(new DrawCardAction(secondMagic));
        atb(new DiscardAction(adp(), adp(), secondMagic, false));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}