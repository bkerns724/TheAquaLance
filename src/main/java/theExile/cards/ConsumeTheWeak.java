package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class ConsumeTheWeak extends AbstractExileCard {
    public final static String ID = makeID(ConsumeTheWeak.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 2;

    public ConsumeTheWeak() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.DARK);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int temp = baseDamage;
        int count = 0;
        if (adp() != null)
            for (AbstractPower pow : adp().powers)
                if (pow.type == AbstractPower.PowerType.BUFF)
                    count++;
        baseDamage += magicNumber*count;
        super.calculateCardDamage(mo);
        baseDamage = temp;
        isDamageModified = damage != baseDamage;
    }

    @Override
    public void applyPowers() {
        int temp = baseDamage;
        int count = 0;
        if (adp() != null)
            for (AbstractPower pow : adp().powers)
                if (pow.type == AbstractPower.PowerType.BUFF)
                    count++;
        baseDamage += magicNumber*count;
        super.applyPowers();
        baseDamage = temp;
        isDamageModified = damage != baseDamage;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}