package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.FinisherAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class BloodBender extends AbstractEasyCard {
    public final static String ID = makeID("BloodBender");
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public BloodBender() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int shardCount = getShardCount(m);
        for (int i = 0; i < shardCount; i++)
            dmg(m, AbstractGameAction.AttackEffect.POISON);
        atb(new FinisherAction(m));
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = baseDamage;
        int debuffCount = getDebuffCount(mo);
        baseDamage = realBaseDamage + debuffCount*magicNumber;
        super.calculateCardDamage(mo);
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeDamage(UPGRADE_DAMAGE);
    }
}