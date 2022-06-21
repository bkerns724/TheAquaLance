package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;

public class ColdSigil extends AbstractArcanistCard {
    public final static String ID = makeID(ColdSigil.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public ColdSigil() {
        super(ID, -2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseDamage = DAMAGE;
        isMultiDamage = true;
        addModifier(elenum.ICE);
        sigil = true;
    }

    @Override
    public void onUse(AbstractPlayer p, AbstractMonster m) {
        allDmg();
    }

    @Override
    public void applyPowers() {
        int temp = baseDamage;
        baseDamage = temp + magicNumber*getBuffCount();
        super.applyPowers();
        baseDamage = temp;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int temp = baseDamage;
        baseDamage = temp + magicNumber*getBuffCount();
        super.calculateCardDamage(mo);
        baseDamage = temp;
    }

    public int getBuffCount() {
        int count = 0;
        for (AbstractPower pow : adp().powers)
            if (pow.type == AbstractPower.PowerType.BUFF)
                count++;

        return count;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeDamage(UPGRADE_DAMAGE);
    }
}