package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.ChaoticMindPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class ChaoticMind extends AbstractArcanistCard {
    public final static String ID = makeID("ChaoticMind");
    private final static int COST = 0;
    private final static int MAGIC = 1;

    public ChaoticMind() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ChaoticMindPower(adp(), magicNumber));
    }

    public void upp() {
        exhaust = false;
    }
}