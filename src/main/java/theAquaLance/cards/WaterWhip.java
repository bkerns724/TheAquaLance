package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.actions.AttackHologramAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class WaterWhip extends AbstractEasyCard {
    public final static String ID = makeID("WaterWhip");
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int COST = 1;

    public WaterWhip() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AquaLanceMod.Enums.WATER);
        atb(new AttackHologramAction());
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}