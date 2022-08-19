package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.getDebuffCount;

public class BoltFromTheBlue extends AbstractExileCard {
    public final static String ID = makeID(BoltFromTheBlue.class.getSimpleName());
    private final static int DAMAGE = 30;
    private final static int UPGRADE_DAMAGE = 5;
    private final static int MAGIC = 5;
    private final static int COST = 1;

    public BoltFromTheBlue() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.LIGHTNING);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        int count = getDebuffCount(m);
        if (count >= 5)
            return true;
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}