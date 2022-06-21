package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.cards.cardUtil.Resonance;

public abstract class AbstractResonantCard extends AbstractArcanistCard {
    public Resonance resonance;
    public int extraDraw = 0;
    public int extraEnergy = 0;

    public AbstractResonantCard(String id, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(id, cost, type, rarity, target);
        if (resonance != null) {
            resonance.draw = extraDraw;
            resonance.energy = extraEnergy;
        }
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        resonance.resonanceEffects(this, m);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        resonance.toPower();
    }

    @Override
    public void initializeDescription() {
        if (resonance != null) {
            rawDescription = resonance.getDescription();
            overrideRawDesc = true;
        } else
            overrideRawDesc = false;
        super.initializeDescription();
    }

    public void upp() {
    }
}