package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.patches.AbstractCardPatch;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class WaterBurst extends AbstractEasyCard {
    public final static String ID = makeID("WaterBurst");
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int MAGIC = 2;

    public WaterBurst() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        AbstractCardPatch.AbstractCardField.replenish.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            dmg(m, AquaLanceMod.Enums.WATER);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}