package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.AbstractCardPatch;
import theAquaLance.powers.TempNegStrengthPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class WaterSigil extends AbstractSigilCard {
    public final static String ID = makeID("WaterSigil");
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;

    public WaterSigil() {
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
        forAllMonstersLiving(m-> applyToEnemy(m, new TempNegStrengthPower(m, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}