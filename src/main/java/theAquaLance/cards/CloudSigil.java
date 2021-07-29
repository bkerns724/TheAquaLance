package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.ReturnToHandAction;
import theAquaLance.patches.AbstractCardPatch;
import theAquaLance.powers.IntelligencePower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class CloudSigil extends AbstractSigilCard {
    public final static String ID = makeID("CloudSigil");
    private final static int MAGIC = 1;

    public CloudSigil() {
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
        applyToSelf(new IntelligencePower(adp(), magicNumber));
        atb(new ReturnToHandAction(this));
    }

    public void upp() {
        uDesc();
    }
}