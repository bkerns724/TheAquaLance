package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.CurseSoulPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class CurseSoul extends AbstractExileCard {
    public final static String ID = makeID(CurseSoul.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 3;

    public CurseSoul() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
        isEthereal = true;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        applyToEnemy(m, new CurseSoulPower(m, magicNumber));
    }

    public void upp() {
        isEthereal = false;
    }
}