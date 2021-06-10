package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.util.Wiz;

import static theAquaLance.AquaLanceMod.makeID;

public class Crash extends AbstractEasyCard {
    public final static String ID = makeID("Crash");
    private final static int DAMAGE = 6;
    private final static int MAGIC = 3;
    private final static int UPG_MAGIC = 3;

    public Crash() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int realBaseDamage = baseDamage;
        baseDamage -= magicNumber;
        calculateCardDamage(m);
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        baseDamage = realBaseDamage;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = baseDamage;
        baseDamage = realBaseDamage + getAttackCount()*magicNumber;
        super.calculateCardDamage(mo);
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    @Override
    public void applyPowers() {
        int realBaseDamage = baseDamage;
        baseDamage = realBaseDamage + getAttackCount()*magicNumber;
        super.applyPowers();
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    private int getAttackCount() {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn)
            if (c.type == CardType.ATTACK)
                count++;
        return count;
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}