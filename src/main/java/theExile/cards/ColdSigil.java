package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.getLowestHealthEnemy;

public class ColdSigil extends AbstractExileCard {
    public final static String ID = makeID(ColdSigil.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int COST = -2;

    public ColdSigil() {
        super(ID, COST, CardType.ATTACK, CardRarity.BASIC, ExileMod.Enums.AUTOAIM_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        sigil = true;
        addModifier(elenum.ICE);
    }

    @Override
    public AbstractMonster getTarget() {
        return getLowestHealthEnemy();
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