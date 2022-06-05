package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.powers.CrushedPower;

import static theArcanist.ArcanistMod.makeID;

public class Collapse extends AbstractArcanistCard {
    public final static String ID = makeID(Collapse.class.getSimpleName());
    private final static int DAMAGE = 16;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 2;

    public Collapse() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.FORCE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        if (!mo.hasPower(CrushedPower.POWER_ID))
            return;

        AbstractPower crushed = mo.getPower(CrushedPower.POWER_ID);
        crushed.amount *= this.magicNumber;

        super.calculateCardDamage(mo);

        crushed.amount /= this.magicNumber;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}