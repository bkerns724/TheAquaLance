package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import theAquaLance.patches.AbstractCardPatch;
import theAquaLance.powers.AquaDrawNextTurnPower;
import theAquaLance.powers.DrowningPower;
import theAquaLance.powers.EnergizedTurquoisePower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class WhirlpoolSigil extends AbstractEasyCard {
    public final static String ID = makeID("WhirlpoolSigil");
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 1;

    public WhirlpoolSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
        AbstractCardPatch.AbstractCardField.sigil.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void onManualDiscard() {
        applyToSelf(new AquaDrawNextTurnPower(adp(), magicNumber));
        applyToSelf(new EnergizedTurquoisePower(adp(), secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}