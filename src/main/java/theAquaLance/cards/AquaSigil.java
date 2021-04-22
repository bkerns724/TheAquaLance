package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.AquaDrawCardAction;
import theAquaLance.actions.AquaGainEnergyAction;
import theAquaLance.patches.AbstractCardPatch;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class AquaSigil extends AbstractEasyCard {
    public final static String ID = makeID("AquaSigil");
    private final static int MAGIC = 2;
    private final static int UPG_MAGIC = 1;
    private final static int ENERGY_AMOUNT = 1;

    public AquaSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        AbstractCardPatch.AbstractCardField.sigil.set(this, true);
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void onManualDiscard() {
        atb(new AquaGainEnergyAction(ENERGY_AMOUNT));
        atb(new AquaDrawCardAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}