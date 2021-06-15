package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class BloodJerk extends AbstractEasyCard {
    public final static String ID = makeID("BloodJerk");
    private final static int SECOND_DAMAGE = 5;
    private final static int UPGRADE_SECOND = 3;

    public BloodJerk() {
        super(ID, 1,  CardType.SKILL, CardRarity.BASIC, CardTarget.ENEMY);
        baseSecondDamage = SECOND_DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmgTwo(m, AquaLanceMod.Enums.BLOOD);
    }

    public void upp() {
        upgradeSecondDamage(UPGRADE_SECOND);
    }
}