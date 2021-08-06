package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.IntelligencePower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Analyze extends AbstractEasyCard {
    public final static String ID = makeID("Analyze");
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public Analyze() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = getShardCount(m);
        if (upgraded)
            count++;
        if (count > 0)
            applyToSelf(new IntelligencePower(adp(), count));
    }

    public void upp() {
        uDesc();
    }
}