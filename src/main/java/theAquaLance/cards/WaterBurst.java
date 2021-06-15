package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.patches.AbstractCardPatch;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class WaterBurst extends AbstractEasyCard {
    public final static String ID = makeID("WaterBurst");
    private final static int SECOND_DAMAGE = 1;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public WaterBurst() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseSecondDamage = SECOND_DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        AbstractCardPatch.AbstractCardField.replenish.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            dmgTwo(m, AquaLanceMod.Enums.WATER);
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}