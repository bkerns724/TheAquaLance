package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class Storm extends AbstractExileCard {
    public final static String ID = makeID(Storm.class.getSimpleName());
    private final static int DAMAGE = 25;
    private final static int UPGRADE_DAMAGE = 5;
    private final static int MAGIC = 5;
    private final static int COST = 0;

    public Storm() {
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