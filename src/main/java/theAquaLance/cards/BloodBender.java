package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.actions.PopAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class BloodBender extends AbstractEasyCard {
    public final static String ID = makeID("BloodBender");
    private final static int MAGIC = 15;
    private final static int UPGRADE_MAGIC = 5;
    private final static int POP_AMOUNT = 5;
    private final static int DAMAGE = POP_AMOUNT * MAGIC;

    public BloodBender() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int shardCount = getShardCount(m);
        if (shardCount >= POP_AMOUNT) {
            dmg(m, AquaLanceMod.Enums.BLOOD);
            atb(new PopAction(m, POP_AMOUNT));
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return getShardCount(m) >= POP_AMOUNT;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = baseDamage;
        int shardCount = getShardCount(mo);
        baseDamage = shardCount*magicNumber;
        super.calculateCardDamage(mo);
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        baseDamage = POP_AMOUNT*magicNumber;
        upgradedDamage = true;
    }
}