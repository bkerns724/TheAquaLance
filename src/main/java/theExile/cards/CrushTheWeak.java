package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToEnemy;

public class CrushTheWeak extends AbstractExileCard {
    public final static String ID = makeID(CrushTheWeak.class.getSimpleName());
    private final static int DAMAGE = 12;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int COST = 2;

    public CrushTheWeak() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.PHANTASMAL);
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
        int count = 0;
        for (AbstractPower pow : adp().powers)
            if (pow.type == AbstractPower.PowerType.BUFF)
                count++;
        if (count > 0)
            applyToEnemy(m, new JinxPower(m, count));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}