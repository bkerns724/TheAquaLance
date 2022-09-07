package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.WetPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.forAllMonstersLiving;

public class Drench extends AbstractExileCard {
    public final static String ID = makeID(Drench.class.getSimpleName());
    private final static int COST = 1;
    private final static int UPGRADED_COST = 0;

    public Drench() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        if (!upgraded)
            applyToEnemy(m, new WetPower(m, 1));
    }

    @Override
    public void nonTargetUse() {
        if (upgraded)
            forAllMonstersLiving(mon -> applyToEnemy(mon, new WetPower(mon, 1)));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
        target = CardTarget.ALL_ENEMY;
    }
}