package theArcanist.cards.cardUtil;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.cards.AbstractArcanistCard.elenum;
import theArcanist.cards.AbstractResonantCard;
import theArcanist.powers.*;

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
    public int draw = 0;
    public int energy = 0;
    public ArrayList<elenum> damageMods;

    private static final int MERGE_REDUCTION = 4;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Resonance"));

    public Resonance(int damage) {
        amount = damage;
    }

    public void resonanceEffects(AbstractResonantCard card, AbstractMonster m) {
        int hitCount = 1;
        AbstractPower pow = adp().getPower(SplitResonancePower.POWER_ID);
        if (pow != null)
            hitCount += pow.amount;

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
        if (draw > 0)
            cardDraw(draw);
        if (energy > 0)
            atb(new GainEnergyAction(energy));
    }

    public void toPower() {
        Resonance outRes = resClone();
        outRes.amount += outRes.amplify;
        applyToSelf(new ResonatingPower(outRes));
    }

    public void merge(Resonance inRes) {
        amount += inRes.amount - MERGE_REDUCTION;
        block += inRes.block;
        amplify += inRes.amplify;
        decay += inRes.decay;
        revenge += inRes.revenge;
        jinx += inRes.jinx;
        draw += inRes.draw;
        energy += inRes.energy;
        for (elenum e : inRes.damageMods )
            if (!damageMods.contains(e))
                damageMods.add(e);
    }

    public String getDescription() {
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
        if (draw == 1)
            builder.append(uiStrings.TEXT[7]);
        else if (draw > 1)
            builder.append(uiStrings.TEXT[8].replace("!X5!", String.valueOf(draw)));
        if (energy == 1)
            builder.append(uiStrings.TEXT[9]);
        else if (energy == 2)
            builder.append(uiStrings.TEXT[10]);
        else if (energy == 3)
            builder.append(uiStrings.TEXT[11]);
        else if (energy > 3)
            builder.append(uiStrings.TEXT[12].replace("!X6!", String.valueOf(energy)));

        return builder.toString();
    }

    public Resonance resClone()
    {
        Resonance copy = new Resonance(amount);
        copy.block = block;
        copy.decay = decay;
        copy.revenge = revenge;
        copy.jinx = jinx;
        copy.draw = draw;
        copy.energy = energy;
        return copy;
    }
}
