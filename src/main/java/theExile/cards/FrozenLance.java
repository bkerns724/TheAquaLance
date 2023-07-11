package theExile.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.SlowPower;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.ICE;
import static theExile.util.Wiz.atb;

public class FrozenLance extends AbstractExileCard {
    public final static String ID = makeID(FrozenLance.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public FrozenLance() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(ICE);
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
        AbstractPower pow = m.getPower(SlowPower.POWER_ID);
        if (pow != null)
            atb(new ApplyPowerAction(m, m, new SlowPower(m, magicNumber), magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upMagic(UPGRADE_MAGIC);
    }
}