package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.CallOfTheGravePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class CallOfTheGrave extends AbstractExileCard {
    public final static String ID = makeID(CallOfTheGrave.class.getSimpleName());
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 3;

    public CallOfTheGrave() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        applyToEnemy(m, new CallOfTheGravePower(m, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}