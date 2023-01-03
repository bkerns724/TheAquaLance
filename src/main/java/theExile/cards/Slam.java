package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.DiscardNextTurnPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Slam extends AbstractExileCard {
    public final static String ID = makeID(Slam.class.getSimpleName());
    private final static int DAMAGE = 10;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public Slam() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.FORCE);
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
    }

    public void nonTargetUse() {
        applyToSelf(new DiscardNextTurnPower(magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}