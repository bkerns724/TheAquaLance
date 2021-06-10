package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.AbstractCardPatch;
import theAquaLance.powers.TempNegStrengthPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class SirenSigil extends AbstractEasyCard {
    public final static String ID = makeID("SirenSigil");
    private final static int MAGIC = 4;
    private final static int UPG_MAGIC = 1;

    public SirenSigil() {
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
        forAllMonstersLiving((m) -> applyToEnemy(m, new TempNegStrengthPower(m, magicNumber)));
    }
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}