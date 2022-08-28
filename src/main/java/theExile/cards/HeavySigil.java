package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.getHighestHealthEnemy;

public class HeavySigil extends AbstractExileCard {
    public final static String ID = makeID(HeavySigil.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 2;

    public HeavySigil() {
        super(ID, -2, CardType.ATTACK, CardRarity.UNCOMMON, ExileMod.Enums.AUTOAIM_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
        addModifier(elenum.FORCE);
    }

    @Override
    public AbstractMonster getTarget() {
        return getHighestHealthEnemy();
    }

    @Override
    public void autoTargetUse(AbstractMonster m) {
        calculateCardDamage(m);
        dmg(m);
    }

    public void applyPowers() {
        AbstractPower strength = adp().getPower(StrengthPower.POWER_ID);
        if (strength != null)
            strength.amount *= magicNumber;

        super.applyPowers();

        if (strength != null)
            strength.amount /= magicNumber;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower strength = adp().getPower(StrengthPower.POWER_ID);
        if (strength != null)
            strength.amount *= magicNumber;

        super.calculateCardDamage(mo);

        if (strength != null)
            strength.amount /= magicNumber;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}