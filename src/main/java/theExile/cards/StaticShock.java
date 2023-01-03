package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class StaticShock extends AbstractExileCard implements StartupCard {
    public final static String ID = makeID(StaticShock.class.getSimpleName());
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 2;
    private final static int COST = 0;

    public StaticShock() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.LIGHTNING);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        Wiz.shuffleIn(this, magicNumber);
        return false;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}