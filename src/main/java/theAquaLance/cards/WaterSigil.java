package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.AbstractCardPatch;
import theAquaLance.powers.DrowningPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class WaterSigil extends AbstractEasyCard {
    public final static String ID = makeID("WaterSigil");
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public WaterSigil() {
        super(ID, -2, CardType.ATTACK, CardRarity.BASIC, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        AbstractCardPatch.AbstractCardField.sigil.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void onManualDiscard() {
        forAllMonstersLiving((m) -> applyToEnemy(m, new DrowningPower(m, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}