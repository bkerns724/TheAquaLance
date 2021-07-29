package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.AbstractCardPatch;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class WaterfallSigil extends AbstractSigilCard {
    public final static String ID = makeID("WaterfallSigil");
    private final static int MAGIC = 2;
    private final static int UPG_MAGIC = 1;

    public WaterfallSigil() {
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
        atb(new GainEnergyAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        uDesc();
    }
}