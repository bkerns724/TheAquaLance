package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theExile.powers.ChargePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class Storm extends AbstractExileCard {
    public final static String ID = makeID(Storm.class.getSimpleName());
    private final static int DAMAGE = 25;
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 3;

    public Storm() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.LIGHTNING);
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
    }

    public void applyPowers() {
        AbstractPower charge = adp().getPower(ChargePower.POWER_ID);
        if (charge != null)
            charge.amount *= this.magicNumber;
        super.applyPowers();
        if (charge != null)
            charge.amount /= this.magicNumber;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower charge = adp().getPower(ChargePower.POWER_ID);
        if (charge != null)
            charge.amount *= this.magicNumber;
        super.calculateCardDamage(mo);
        if (charge != null)
            charge.amount /= this.magicNumber;
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}