package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.FinisherAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class BloodBender extends AbstractEasyCard {
    public final static String ID = makeID("BloodBender");
    private final static int DAMAGE = 0;
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;

    public BloodBender() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int debuffCount = getDebuffCount(m);
        for (int i = 0; i < debuffCount; i++)
            dmg(m, AbstractGameAction.AttackEffect.POISON);
        atb(new FinisherAction(m));
        atb(new RemoveDebuffsAction(m));
        atb(new RemoveSpecificPowerAction(m, p, "Shackled"));
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = baseDamage;
        int shardCount = getShardCount(mo);
        baseDamage = magicNumber*shardCount;
        super.calculateCardDamage(mo);
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}