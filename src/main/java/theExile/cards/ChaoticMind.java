package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.ChaoticMindPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class ChaoticMind extends AbstractExileCard {
    public final static String ID = makeID(ChaoticMind.class.getSimpleName());
    private final static int COST = 0;
    private final static int MAGIC = 1;

    public ChaoticMind() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
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