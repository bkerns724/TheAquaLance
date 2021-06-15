package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class PressurizedJet extends AbstractEasyCard {
    public final static String ID = makeID("PressurizedJet");
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_DAMAGE = 2;

    public PressurizedJet() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseSecondDamage = SECOND_DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            dmgTwo(m, AquaLanceMod.Enums.WATER);
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}