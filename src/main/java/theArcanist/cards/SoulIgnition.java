package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.getDebuffCount;

public class SoulIgnition extends AbstractArcanistCard {
    public final static String ID = makeID(SoulIgnition.class.getSimpleName());
    private final static int DAMAGE = 30;
    private final static int UPGRADE_DAMAGE = 10;
    private final static int MAGIC = 5;
    private final static int COST = 0;

    public SoulIgnition() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.FIRE);
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