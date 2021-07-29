package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theAquaLance.patches.AbstractCardPatch;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class RainSigil extends AbstractSigilCard {
    public final static String ID = makeID("RainSigil");
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public RainSigil() {
        super(ID, CardRarity.UNCOMMON);
        baseMagicNumber = magicNumber = MAGIC;
        AbstractCardPatch.AbstractCardField.sigil.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void onManualDiscard() {
        forAllMonstersLiving(m -> applyToEnemy(m, new WeakPower(m, magicNumber, false)));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}