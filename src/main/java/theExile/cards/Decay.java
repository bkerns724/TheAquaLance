package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.DecayPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.getJinxAmount;

public class Decay extends AbstractExileCard {
    public final static String ID = makeID(Decay.class.getSimpleName());
    private final static int COST = 1;

    public Decay() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
    }

    public void applyAttributes() {
    }

    public void singleTargetUse(AbstractMonster m) {
        int count = getJinxAmount(m);
        if (upgraded)
            count++;
        if (count > 0)
            applyToEnemy(m, new DecayPower(m, count));
    }

    public void upp() {

    }
}