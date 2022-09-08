package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class Drain extends AbstractExileCard {
    public final static String ID = makeID(Drain.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 5;

    public Drain() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        applyToEnemy(m, new StrengthPower(m, -magicNumber));
        if (!m.hasPower(ArtifactPower.POWER_ID))
            applyToSelf(new StrengthPower(adp(), magicNumber));
    }

    public void onPlayEldritchCard() {
        updateCost(-1);
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}