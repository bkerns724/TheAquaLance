package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theAquaLance.patches.AbstractCardPatch;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class MarshSigil extends AbstractEasyCard {
    public final static String ID = makeID("MarshSigil");
    private final static int MAGIC = 7;
    private final static int UPGRADE_MAGIC = 2;

    public MarshSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        AbstractCardPatch.AbstractCardField.sigil.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void onManualDiscard() {
        forAllMonstersLiving((m) -> applyToEnemy(m, new PoisonPower(m, adp(), magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}