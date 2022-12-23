package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class FrigidSigil extends AbstractExileCard {
    public final static String ID = makeID(FrigidSigil.class.getSimpleName());
    private final static int COST = -2;
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;

    public FrigidSigil() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, ExileMod.Enums.AUTOAIM_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.ICE);
        sigil = true;
    }

    @Override
    public AbstractMonster getTarget() {
        return Wiz.getHighestHealthEnemy();
    }

    @Override
    public void autoTargetUse(AbstractMonster m) {
        calculateCardDamage(m);
        for (int i = 0; i < ExileMod.attacksThisTurn; i++)
            dmg(m);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}