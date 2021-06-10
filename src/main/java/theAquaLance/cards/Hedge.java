package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.AbstractCardPatch;
import theAquaLance.powers.HedgePower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Hedge extends AbstractEasyCard {
    public final static String ID = makeID("Hedge");
    private final static int MAGIC = 1;

    public Hedge() {
        super(ID, 0, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new HedgePower(p, magicNumber));
    }

    public void upp() {
        AbstractCardPatch.AbstractCardField.replenish.set(this, true);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}