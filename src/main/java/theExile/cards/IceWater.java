package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.BindingHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theExile.powers.FrostbitePower;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.ICE;
import static theExile.util.Wiz.*;

public class IceWater extends AbstractExileCard {
    public final static String ID = makeID(IceWater.class.getSimpleName());
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 1;
    private final static int COST = 2;

    public IceWater() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(ICE);
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (m == null)
                    return;
                AbstractPower pow = m.getPower(FrostbitePower.POWER_ID);
                if (!m.isDeadOrEscaped() && pow != null) {
                    DamageInfo info = BindingHelper.makeInfo(new ArrayList<>(), adp(), pow.amount, DamageInfo.DamageType.HP_LOSS);
                    att(new DamageAction(m, info));
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}