package theExile.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class FleshySigil extends AbstractExileCard {
    public final static String ID = makeID(FleshySigil.class.getSimpleName());
    private final static int DAMAGE = 12;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int COST = -2;

    public FleshySigil() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        sigil = true;
        baseDamage = DAMAGE;
        isMultiDamage = true;
    }

    @Override
    public void nonTargetUse() {
        AbstractMonster m = Wiz.getRandomEnemy();
        calculateCardDamage(m);
        if (damageModList.size() == 0)
            dmg(m, ExileMod.Enums.BLOOD);
        else
            dmg(m);
        atb(new MakeTempCardInHandAction(makeStatEquivalentCopy(), 1));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}