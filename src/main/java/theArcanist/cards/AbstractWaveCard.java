package theArcanist.cards;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.patches.AbstractCardPatch;

import java.util.ArrayList;
import java.util.List;

import static theArcanist.util.Wiz.*;

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
        AbstractCardPatch.AbstractCardField.resonance.set(this, true);
        mergedCards = new ArrayList<>();
        if (rarity == CardRarity.UNCOMMON)
            mergeValue = 1;
        if (rarity == CardRarity.RARE)
            mergeValue = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //atb(new WaveMergeAction(this) );
        //atb(new WaveAction(this, m));
    }

    @Override
    public List<String> getCardDescriptors() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Wave");
        return retVal;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(BaseMod.getKeywordTitle("aqualancemod:Wave"),
                BaseMod.getKeywordDescription("aqualancemod:Wave")));
        return retVal;
    }

    public abstract void upp();

    public void addCard(AbstractWaveCard card) {
        card.angle = 0.0F;
        card.targetAngle = angle;
        mergedCards.add(card);
        cardToPreview.add(card);
    }

    // Currently not used, but it feels like I should have this function
    public void removeCard(AbstractWaveCard card) {
        mergedCards.remove(card);
    }

    public void calculateBonuses() {
        baseDamage = damageCore;
        baseBlock = blockCore;
        baseMagicNumber = magicCore;
        areaAttack = areaCore;
        for (AbstractWaveCard c : mergedCards) {
            baseDamage += c.damageBonus;
            baseBlock += c.blockBonus;
            baseMagicNumber += c.magicBonus;
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
        if (magicNumber > 0)
            rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        initializeDescription();
    }
}