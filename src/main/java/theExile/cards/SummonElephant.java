package theExile.cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import theExile.vfx.ElephantDropEffect;
import theExile.vfx.LongElephantDropEffect;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class SummonElephant extends AbstractExileCard {
    public final static String ID = makeID(SummonElephant.class.getSimpleName());
    private final static int DAMAGE = 14;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 3;
    private final static int COST = 1;
    private static boolean seenThisSession;

    public SummonElephant() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
        isMultiDamage = true;
    }

    @Override
    public void nonTargetUse() {
        if (!seenThisSession) {
            vfx(new LongElephantDropEffect(), 2.35f);
            seenThisSession = true;
        }  else if (MathUtils.random(0, 1f) < 0.08f)
            vfx(new LongElephantDropEffect(), 2.35f);
        else
            vfx(new ElephantDropEffect(), 0.45f);

        allDmg(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        DamageInfo info = new DamageInfo(adp(), magicNumber, DamageInfo.DamageType.THORNS);
        atb(new DamageAction(adp(), info, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }

    static {
        seenThisSession = false;
    }
}