package theExile.cards;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theExile.ExileMod;
import theExile.powers.FrostbitePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class Shatter extends AbstractExileCard {
    public final static String ID = makeID(Shatter.class.getSimpleName());
    private final static int COST = 2;

    public Shatter() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        exhaust = true;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        AbstractPower pow = m.getPower(FrostbitePower.POWER_ID);
        if (pow == null)
            return;
        atb(new LoseHPAction(m, adp(), pow.amount, ExileMod.Enums.ICE));
    }

    public void upp() {
        exhaust = false;
    }
}