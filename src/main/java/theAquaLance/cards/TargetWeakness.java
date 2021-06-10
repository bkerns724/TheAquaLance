package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class TargetWeakness extends AbstractEasyCard {
    public final static String ID = makeID("TargetWeakness");
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public TargetWeakness() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = baseDamage;
        int debuffCount = getDebuffCount(mo);
        baseDamage = realBaseDamage + magicNumber*debuffCount;
        super.calculateCardDamage(mo);
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}