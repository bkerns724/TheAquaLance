package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Grease extends AbstractArcanistCard {
    public final static String ID = makeID("Grease");
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 0;

    public Grease() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}