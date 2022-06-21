package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.SplitResonancePower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.applyToSelf;

public class SplitResonance extends AbstractArcanistCard {
    public final static String ID = makeID(SplitResonance.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 0;

    public SplitResonance() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SplitResonancePower(adp(), magicNumber));
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    public void upp() {
    }
}