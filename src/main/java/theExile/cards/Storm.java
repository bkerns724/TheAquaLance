package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.getDebuffCount;
import static theExile.util.Wiz.getEnemies;

public class Storm extends AbstractExileCard {
    public final static String ID = makeID(Storm.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 6;
    private final static int COST = 1;

    public Storm() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
        addModifier(elenum.LIGHTNING);
        glowColor = GOLD_BORDER_GLOW_COLOR;
    }

    public void singleTargetUse(AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            dmg(m);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (m == null) {
            for (AbstractMonster mon : getEnemies()) {
                if (getDebuffCount(mon) >= secondMagic)
                    return true;
            }
            cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }

        int count = getDebuffCount(m);
        if (count >= secondMagic)
            return true;
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}