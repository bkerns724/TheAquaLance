package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.getDebuffCount;

public class BallLightning extends AbstractExileCard {
    public final static String ID = makeID(BallLightning.class.getSimpleName());
    private final static int DAMAGE = 25;
    private final static int UPGRADE_DAMAGE = 7;
    private final static int MAGIC = 5;
    private final static int COST = 0;

    public BallLightning() {
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