package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.WeakIfEmbedAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class BroadsideSlap extends AbstractEasyCard {
    public final static String ID = makeID("BroadsideSlap");
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;

    public BroadsideSlap() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        atb(new WeakIfEmbedAction(m, magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}