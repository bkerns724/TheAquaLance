package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class TinglingSigil extends AbstractExileCard {
    public final static String ID = makeID(TinglingSigil.class.getSimpleName());
    private final static int COST = -2;
    private final static int DAMAGE = 11;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 1;

    public TinglingSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, ExileMod.Enums.AUTOAIM_ENEMY);
    }

    public void applyAttributes() {
        sigil = true;
        addModifier(elenum.LIGHTNING);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public AbstractMonster getTarget() {
        return Wiz.getLowestHealthEnemy();
    }

    @Override
    public void autoTargetUse(AbstractMonster m) {
        calculateCardDamage(m);
        dmg(m);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}