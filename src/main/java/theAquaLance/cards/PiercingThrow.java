package theAquaLance.cards;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.PiercingThrowAction;
import theAquaLance.cards.AbstractEasyCard;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class PiercingThrow extends AbstractEasyCard {
    public final static String ID = makeID("PiercingThrow");
    // intellij stuff attack, enemy, common, 10, 4, , , 1, , 1, 
    private final static int DAMAGE = 10;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 1;

    public PiercingThrow() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new PiercingThrowAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}