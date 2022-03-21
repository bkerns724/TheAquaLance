package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.powers.FearPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Fear extends AbstractArcanistCard {
    public final static String ID = makeID("Fear");
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 0;

    public Fear() {
        super(ID, COST, CardType.SKILL, ArcanistMod.Enums.UNIQUE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        magicOneIsDebuff = true;
        exhaust = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            applyToEnemy(m, new FearPower(m));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}