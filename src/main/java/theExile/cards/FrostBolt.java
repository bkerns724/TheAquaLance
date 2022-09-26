package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.EnergizedExilePower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class FrostBolt extends AbstractExileCard {
    public final static String ID = makeID(FrostBolt.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 1;

    public FrostBolt() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.ICE);
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
    }

    @Override
    public void nonTargetUse() {
        Wiz.applyToSelf(new EnergizedExilePower(1));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}