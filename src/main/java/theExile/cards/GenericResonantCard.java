package theExile.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theExile.cards.cardUtil.Resonance;

import static theExile.ExileMod.makeID;

@AutoAdd.Ignore
public class GenericResonantCard extends AbstractResonantCard {
    public final static String ID = makeID(GenericResonantCard.class.getSimpleName());
    private final static int COST = 1;
    private final static int UPGRADE_DAMAGE = 4;

    public GenericResonantCard(Resonance resonance) {
        super(ID, COST, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.resonance = resonance;
        if (resonance.getDamage() <= 0) {
            type = CardType.SKILL;
            if (resonance.jinx > 0)
                target = CardTarget.ENEMY;
            else
                target = CardTarget.SELF;
        }
        initializeDescription();
    }

    @Override
    protected void setResonance() {
    }

    @Override
    public void applyAttributes() {
    }

    // Should not be used
    public GenericResonantCard() {
        this(new Resonance());
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    public void upp() {
        baseDamage += UPGRADE_DAMAGE;
        resonance.damage = UPGRADE_DAMAGE;
    }

    @Override
    public AbstractCard makeCopy() {
        return new GenericResonantCard(resonance.resClone());
    }
}