package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.GameActionManagerPatch;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Current extends AbstractEasyCard {
    public final static String ID = makeID("Current");
    private final static int DAMAGE = 10;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 10;
    private final static int UPGRADE_MAGIC = 3;

    public Current() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int deathCount = GameActionManagerPatch.GameActionManagerField.deathsThisCombat.get(AbstractDungeon.actionManager);
        if (deathCount < 3)
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        else
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    public void applyPowers() {
        int realBaseDamage = baseDamage;
        int deathCount = GameActionManagerPatch.GameActionManagerField.deathsThisCombat.get(AbstractDungeon.actionManager);
        baseDamage = magicNumber*deathCount;
        super.applyPowers();
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = baseDamage;
        int deathCount = GameActionManagerPatch.GameActionManagerField.deathsThisCombat.get(AbstractDungeon.actionManager);
        baseDamage = magicNumber*deathCount;
        super.calculateCardDamage(mo);
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}