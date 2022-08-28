package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class SpinningSigil extends AbstractExileCard {
    public final static String ID = makeID(SpinningSigil.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = -2;

    public SpinningSigil() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, ExileMod.Enums.AUTOAIM_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        sigil = true;
    }

    @Override
    public AbstractMonster getTarget() {
        return getHighestHealthEnemy();
    }

    @Override
    public void autoTargetUse(AbstractMonster m) {
        calculateCardDamage(m);
        dmg(m);
    }

    @Override
    public void nonTargetUse() {
        cardDraw(1);
        discard(1);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}