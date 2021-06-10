package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.AbstractCardPatch;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class ThunderheadSigil extends AbstractEasyCard {
    public final static String ID = makeID("ThunderheadSigil");
    private final static int MAGIC = 1;
    private final static int UPG_MAGIC = 1;

    public ThunderheadSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
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
    }
}