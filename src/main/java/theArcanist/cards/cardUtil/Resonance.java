package theArcanist.cards.cardUtil;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.cards.AbstractArcanistCard.elenum;
import theArcanist.cards.AbstractResonantCard;
import theArcanist.powers.*;
import theArcanist.relics.TuningFork;

import java.util.ArrayList;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Resonance {
    public int amount;
    public int block = 0;
    public int amplify = 0;
    public int decay = 0;
    public int revenge = 0;
    public int jinx = 0;
    public int extraDraw = 0;
    public int extraEnergy = 0;
    public ArrayList<elenum> damageMods = new ArrayList<>();

    private static final int MERGE_REDUCTION = 4;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Resonance"));
    private static final UIStrings uiStringsConcise = CardCrawlGame.languagePack.getUIString(makeID("ResonanceConcise"));

    public Resonance(int damage) {
        amount = damage;
    }

    public void resonanceEffects(AbstractResonantCard card, AbstractMonster m) {
        int hitCount = 1;
        if (adp() != null && adp().hasPower(SplitResonancePower.POWER_ID))
            hitCount += adp().getPower(SplitResonancePower.POWER_ID).amount;

        card.baseDamage = amount/hitCount;
        card.baseBlock = block;
        card.calculateCardDamage(m);
        for (int i = 0; i < hitCount; i++)
            card.dmg(m);
        if (card.baseBlock > 0)
            card.blck();
        if (decay > 0)
            applyToEnemy(m, new DecayingPower(m, decay));
        if (revenge > 0)
            applyToSelf(new SoulFlameBarrierPower(adp(), revenge));
        if (jinx > 0)
            applyToEnemy(m, new JinxPower(m, jinx));
        if (extraDraw > 0)
            cardDraw(extraDraw);
        if (extraEnergy > 0)
            atb(new GainEnergyAction(extraEnergy));
    }

    public void toPower() {
        Resonance outRes = resClone();
        outRes.amount += outRes.amplify;
        applyToSelf(new ResonatingPower(outRes));
    }

    public void merge(Resonance inRes) {
        amount += inRes.amount - MERGE_REDUCTION;
        if (adp().hasRelic(TuningFork.ID))
            amount += TuningFork.BOOST_AMOUNT;
        block += inRes.block;
        amplify += inRes.amplify;
        decay += inRes.decay;
        revenge += inRes.revenge;
        jinx += inRes.jinx;
        extraDraw += inRes.extraDraw;
        extraEnergy += inRes.extraEnergy;
        for (elenum e : inRes.damageMods )
            if (!damageMods.contains(e))
                damageMods.add(e);
    }

    public String getDescription() {
        int count = 0;
        if (block > 0)
            count++;
        if (amplify > 0)
            count++;
        if (decay > 0)
            count ++;
        if (revenge > 0)
            count += 3;
        if (jinx > 0)
            count++;
        if (extraDraw > 0)
            count++;
        if (extraEnergy > 0)
            count++;

        if (count >= 5)
            return getConciseDescription();

        int hitCount = 1;
        if (adp() != null && adp().hasPower(SplitResonancePower.POWER_ID))
            hitCount += adp().getPower(SplitResonancePower.POWER_ID).amount;

        StringBuilder builder;
        if (hitCount == 1)
            builder = new StringBuilder(uiStrings.TEXT[0]);
        else
            builder = new StringBuilder(uiStrings.TEXT[1].replace("!X0!", String.valueOf(hitCount)));

        if (block > 0)
            builder.append(uiStrings.TEXT[2]);
        if (amplify > 0)
            builder.append(uiStrings.TEXT[3].replace("!X1!", String.valueOf(amplify)));
        if (decay > 0)
            builder.append(uiStrings.TEXT[4].replace("!X2!", String.valueOf(decay)));
        if (revenge > 0)
            builder.append(uiStrings.TEXT[5].replace("!X3!", String.valueOf(revenge)));
        if (jinx > 0)
            builder.append(uiStrings.TEXT[6].replace("!X4!", String.valueOf(jinx)));
        if (extraDraw == 1)
            builder.append(uiStrings.TEXT[7]);
        else if (extraDraw > 1)
            builder.append(uiStrings.TEXT[8].replace("!X5!", String.valueOf(extraDraw)));
        if (extraEnergy == 1)
            builder.append(uiStrings.TEXT[9]);
        else if (extraEnergy == 2)
            builder.append(uiStrings.TEXT[10]);
        else if (extraEnergy == 3)
            builder.append(uiStrings.TEXT[11]);
        else if (extraEnergy > 3)
            builder.append(uiStrings.TEXT[12].replace("!X6!", String.valueOf(extraEnergy)));

        return builder.toString();
    }

    public int getDamage() {
        int hitCount = 1;
        if (adp() != null && adp().hasPower(SplitResonancePower.POWER_ID))
            hitCount += adp().getPower(SplitResonancePower.POWER_ID).amount;

        return amount/hitCount;
    }

    private String getConciseDescription() {
        int hitCount = 1;
        if (adp() != null && adp().hasPower(SplitResonancePower.POWER_ID))
            hitCount += adp().getPower(SplitResonancePower.POWER_ID).amount;

        StringBuilder builder;
        if (hitCount == 1)
            builder = new StringBuilder(uiStringsConcise.TEXT[0]);
        else
            builder = new StringBuilder(uiStringsConcise.TEXT[1].replace("!X0!", String.valueOf(hitCount)));

        boolean newLine = true;

        if (block > 0) {
            newLine = addNewLine(newLine, builder);
            builder.append(uiStringsConcise.TEXT[2]);
        }
        if (amplify > 0) {
            newLine = addNewLine(newLine, builder);
            builder.append(uiStringsConcise.TEXT[3].replace("!X1!", String.valueOf(amplify)));
        }
        if (decay > 0) {
            newLine = addNewLine(newLine, builder);
            builder.append(uiStringsConcise.TEXT[4].replace("!X2!", String.valueOf(decay)));
        }
        if (revenge > 0) {
            newLine = addNewLine(newLine, builder);
            builder.append(uiStringsConcise.TEXT[5].replace("!X3!", String.valueOf(revenge)));
        }
        if (jinx > 0) {
            newLine = addNewLine(newLine, builder);
            builder.append(uiStringsConcise.TEXT[6].replace("!X4!", String.valueOf(jinx)));
        }
        if (extraDraw > 0) {
            newLine = addNewLine(newLine, builder);
            builder.append(uiStringsConcise.TEXT[7].replace("!X5!", String.valueOf(extraDraw)));
        }
        if (extraEnergy > 0) {
            addNewLine(newLine, builder);
            builder.append(uiStringsConcise.TEXT[8].replace("!X6!", String.valueOf(extraEnergy)));
        }

        return builder.toString();
    }

    public Boolean addNewLine(boolean newLine, StringBuilder builder) {
        if (newLine) {
            builder.append(" NL");
            return false;
        } else
            return true;
    }

    public Resonance resClone()
    {
        Resonance copy = new Resonance(amount);
        copy.block = block;
        copy.amplify = amplify;
        copy.decay = decay;
        copy.revenge = revenge;
        copy.jinx = jinx;
        copy.extraDraw = extraDraw;
        copy.extraEnergy = extraEnergy;
        copy.damageMods.addAll(damageMods);
        return copy;
    }
}