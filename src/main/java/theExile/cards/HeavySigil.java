package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theExile.ExileMod;
import theExile.powers.CrushedPower;

import static theExile.ExileMod.makeID;
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

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower crushed = mo.getPower(CrushedPower.POWER_ID);
        if (crushed != null)
            crushed.amount *= magicNumber;

        super.calculateCardDamage(mo);

        if (crushed != null)
            crushed.amount /= magicNumber;

        isDamageModified = damage != baseDamage;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}