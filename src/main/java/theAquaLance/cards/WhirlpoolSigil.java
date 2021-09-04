package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;

import static theAquaLance.AquaLanceMod.makeID;

public class WhirlpoolSigil extends AbstractSigilCard {
    public final static String ID = makeID("WhirlpoolSigil");
    private final static int SECOND_DAMAGE = 10;
    private final static int UPGRADE_SECOND = 3;

    public WhirlpoolSigil() {
        super(ID, CardRarity.COMMON);
        baseSecondDamage = SECOND_DAMAGE;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void onManualDiscard() {
        calculateCardDamage(null);
        allDmgTwoTop(AquaLanceMod.Enums.WATER);
    }

    public void upp() {
        upgradeSecondDamage(UPGRADE_SECOND);
    }
}