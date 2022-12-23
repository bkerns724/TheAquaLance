package theExile.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class BoomerangSigil extends AbstractExileCard {
    public final static String ID = makeID(BoomerangSigil.class.getSimpleName());
    private final static int DAMAGE = 10;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = -2;

    public BoomerangSigil() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        sigil = true;
        baseDamage = DAMAGE;
        exhaust = true;
        isMultiDamage = true;
    }

    @Override
    public void nonTargetUse() {
        AbstractMonster m = Wiz.getRandomEnemy();
        calculateCardDamage(m);
        if (damageModList.size() == 0)
            dmg(m, Wiz.getBluntEffect(damage));
        else
            dmg(m);
        atb(new MakeTempCardInHandAction(this.makeStatEquivalentCopy(), 1));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}