package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class BoltFromTheBlue extends AbstractExileCard {
    public final static String ID = makeID(BoltFromTheBlue.class.getSimpleName());
    private final static int DAMAGE = 30;
    private final static int UPGRADE_DAMAGE = 10;
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
        glowColor = GOLD_BORDER_GLOW_COLOR;
    }

    public void singleTargetUse(AbstractMonster m) {
        if (getDebuffCount(m) >= magicNumber)
            dmg(m);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (m == null) {
            for (AbstractMonster mon : getEnemies()) {
                if (getDebuffCount(mon) >= magicNumber)
                    return true;
            }
            cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }

        int count = getDebuffCount(m);
        if (count >= magicNumber)
            return true;
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}