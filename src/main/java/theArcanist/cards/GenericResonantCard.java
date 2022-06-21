package theArcanist.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theArcanist.cards.cardUtil.Resonance;

import static theArcanist.ArcanistMod.makeID;

@AutoAdd.Ignore
public class GenericResonantCard extends AbstractResonantCard {
    public final static String ID = makeID(GenericResonantCard.class.getSimpleName());
    private final static int COST = 1;

    public GenericResonantCard(Resonance resonance) {
        super(ID, COST, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.resonance = resonance;
        initializeDescription();
    }

    @Override
    public void applyAttributes() {
    }

    // Should not be used
    public GenericResonantCard() {
        this(new Resonance(8));
    }

    public void upp() {
        baseDamage += 4;
    }

    @Override
    public AbstractCard makeCopy() {
        return new GenericResonantCard(resonance.resClone());
    }
}