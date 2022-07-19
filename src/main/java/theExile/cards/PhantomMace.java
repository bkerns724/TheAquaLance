package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.TempNegStrengthPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class PhantomMace extends AbstractExileCard {
    public final static String ID = makeID(PhantomMace.class.getSimpleName());
    private final static int DAMAGE = 15;
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 5;
    private final static int COST = 2;

    public PhantomMace() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.FORCE);
        magicOneIsDebuff = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
        applyToEnemy(m, new TempNegStrengthPower(m, magicNumber));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int temp = baseDamage;
        if (mo != null && mo.getIntentBaseDmg() >= 0)
            baseDamage += magicNumber;
        super.calculateCardDamage(mo);
        baseDamage = temp;
        isDamageModified = baseDamage != damage;
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}