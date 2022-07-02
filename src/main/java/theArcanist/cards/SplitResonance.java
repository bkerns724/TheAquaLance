package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.cards.cardUtil.Resonance;
import theArcanist.powers.ResonatingPower;
import theArcanist.powers.SplitResonancePower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.applyToSelf;

public class SplitResonance extends AbstractArcanistCard {
    public final static String ID = makeID(SplitResonance.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 0;
    private final static int SECOND_MAGIC = 0;
    private final static int UPGRADE_SECOND = 4;

    public SplitResonance() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SplitResonancePower(adp(), magicNumber));
        if (secondMagic > 0)
            applyToSelf(new ResonatingPower(new Resonance(secondMagic)));
    }

    public void upp() {
        upMagic2(UPGRADE_SECOND);
    }
}