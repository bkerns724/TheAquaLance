package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class PickOff extends AbstractEasyCard {
    public final static String ID = makeID("PickOff");
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 8;
    private final static int UPG_MAGIC = 3;

    public PickOff() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster weakestMonster = null;
        Iterator iter = AbstractDungeon.getMonsters().monsters.iterator();
        while(iter.hasNext()) {
            AbstractMonster mo = (AbstractMonster)iter.next();
            if (!mo.isDeadOrEscaped()) {
                if (weakestMonster == null) {
                    weakestMonster = mo;
                } else if (mo.currentHealth < weakestMonster.currentHealth) {
                    weakestMonster = mo;
                }
            }
        }

        int enemyCount = getEnemies().size();
        if (enemyCount < 3)
            dmg(weakestMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        else
            dmg(weakestMonster, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    public void applyPowers() {
        int realBaseDamage = baseDamage;
        int enemyCount = getEnemies().size();
        baseDamage = magicNumber*enemyCount;
        super.applyPowers();
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = baseDamage;
        int enemyCount = getEnemies().size();
        baseDamage = magicNumber*enemyCount;
        super.calculateCardDamage(mo);
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPG_MAGIC);
    }
}