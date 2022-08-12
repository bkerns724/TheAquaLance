package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.getHighestHealthEnemy;

public class HeavySigil extends AbstractExileCard {
    public final static String ID = makeID(HeavySigil.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 2;

    public HeavySigil() {
        super(ID, -2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
        addModifier(elenum.FORCE);
    }

    @Override
    public void onUse(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster strongestMonster = getHighestHealthEnemy();
        calculateCardDamage(strongestMonster);

        dmg(strongestMonster);
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