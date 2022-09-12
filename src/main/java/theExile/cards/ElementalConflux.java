package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;

public class ElementalConflux extends AbstractExileCard {
    public final static String ID = makeID(ElementalConflux.class.getSimpleName());
    private final static int DAMAGE = 16;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int COST = 2;

    public ElementalConflux() {
        super(ID, COST, CardType.ATTACK, ExileMod.Enums.UNIQUE, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.PHANTASMAL);
        addModifier(elenum.ICE);
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}