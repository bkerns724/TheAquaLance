package theAquaLance.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.WaveAction;
import theAquaLance.actions.WaveMergeAction;
import theAquaLance.patches.AbstractCardPatch;
import theAquaLance.relics.SailorCharm;

import java.util.ArrayList;

import static theAquaLance.util.Wiz.*;

@AutoAdd.Ignore
public abstract class AbstractWaveCard extends AbstractEasyCard  {
    public ArrayList<AbstractWaveCard> mergedCards;

    protected int damageCore = 0;
    protected int blockCore = 0;
    protected int magicCore = 0;
    protected boolean areaCore = false;
    public int damageBonus = 0;
    public int blockBonus = 0;
    public int magicBonus = 0;
    public boolean areaAttack = false;
    public int mergeValue = 0;

    public AbstractWaveCard(final String cardID, final int cost, final CardRarity rarity) {
        super(cardID, cost, CardType.ATTACK, rarity, CardTarget.ENEMY);
        AbstractCardPatch.AbstractCardField.boomerang.set(this, true);
        mergedCards = new ArrayList<>();
        if (rarity == CardRarity.UNCOMMON)
            mergeValue = 1;
        if (rarity == CardRarity.RARE)
            mergeValue = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new WaveMergeAction(this));
        atb(new WaveAction(this, m));
    }

    public abstract void upp();

    @Override
    public void onMoveToDiscard() {
        if (!adp().hasRelic(SailorCharm.ID)) {
            for (AbstractWaveCard c : mergedCards) {
                c.current_x = this.hb.cX;
                c.current_y = this.hb.cY;
                adp().hand.moveToDiscardPile(c);
                c.setDescription();
            }
            mergedCards.clear();
            cardToPreview.clear();
            cardsToPreview = null;
            previewIndex = 0;
            setDescription();
        }
    }

    public void addCard(AbstractWaveCard card) {
        card.angle = 0.0F;
        card.targetAngle = angle;
        mergedCards.add(card);
        cardToPreview.add(card);
    }

    public void removeCard(AbstractWaveCard card) {
        mergedCards.remove(card);
    }

    public void calculateBonuses() {
        baseDamage = damageCore;
        baseBlock = blockCore;
        baseSecondDamage = magicCore;
        areaAttack = areaCore;
        for (AbstractWaveCard c : mergedCards) {
            baseDamage += c.damageBonus;
            baseBlock += c.blockBonus;
            baseSecondDamage += c.magicBonus;
            if (c.areaCore)
                areaAttack = true;
        }
        magicNumber = baseMagicNumber;
        setDescription();
    }

    public void setDescription() {
        rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        if (baseBlock > 0)
            rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        if (secondDamage > 0)
            rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        initializeDescription();
    }
}