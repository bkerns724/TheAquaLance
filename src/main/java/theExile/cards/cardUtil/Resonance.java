package theExile.cards.cardUtil;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.cards.AbstractExileCard.elenum;
import theExile.cards.AbstractResonantCard;
import theExile.powers.ErosionPower;
import theExile.powers.JinxPower;
import theExile.powers.LethalChorusPower;
import theExile.powers.ResonatingPower;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class Resonance {
    public int amount = 1;
    public int damage = 0;
    public int multiHit = 1;
    public int block = 0;
    public int erosion = 0;
    public int jinx = 0;
    public int extraDraw = 0;
    public int extraEnergy = 0;
    public ArrayList<elenum> damageMods = new ArrayList<>();
    public static final float ELEMENT_DAMAGE_PENALTY = 0.2f;
    public static final int ELEMENT_DAMAGE_PENALTY_PERCENT = (int)(ELEMENT_DAMAGE_PENALTY*100);

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Resonance"));
    private static final UIStrings uiStringsConcise = CardCrawlGame.languagePack.getUIString(makeID("ResonanceConcise"));

    public Resonance() { }

    public void resonanceEffects(AbstractResonantCard card, AbstractMonster m) {
        card.baseDamage = getDamage();
        card.baseBlock = block;
        card.calculateCardDamage(m);

        if (card.baseDamage > 0) {
            for (int i = 0; i < multiHit; i++)
                card.dmg(m);
        }
        if (card.baseBlock > 0)
            card.blck();
        if (erosion > 0)
            applyToEnemy(m, new ErosionPower(m, erosion));
        if (jinx > 0)
            applyToEnemy(m, new JinxPower(m, jinx));
        if (extraDraw > 0)
            cardDraw(extraDraw);
        if (extraEnergy > 0)
            atb(new GainEnergyAction(extraEnergy));
    }

    public void toPower() {
        Resonance outRes = resClone();
        applyToSelf(new ResonatingPower(outRes));
    }

    public void merge(Resonance inRes) {
        amount += inRes.amount;
        damage += inRes.damage;
        multiHit += inRes.multiHit - 1;
        block += inRes.block;
        erosion += inRes.erosion;
        jinx += inRes.jinx;
        extraDraw += inRes.extraDraw;
        extraEnergy += inRes.extraEnergy;
        for (elenum e : inRes.damageMods )
            if (!damageMods.contains(e))
                damageMods.add(e);
    }

    public String getDescription() {
        int count = 0;
        if (damage > 0)
            count++;
        if (block > 0)
            count++;
        if (erosion > 0)
            count += 2;
        if (jinx > 0)
            count++;
        if (extraDraw > 0)
            count++;
        if (extraEnergy > 0)
            count++;

        if (count >= 6)
            return getConciseDescription();

        StringBuilder builder;
        if (multiHit == 1 && damage > 0)
            builder = new StringBuilder(uiStrings.TEXT[0]);
        else if (damage > 0)
            builder = new StringBuilder(uiStrings.TEXT[1].replace("!X0!", String.valueOf(multiHit)));
        else
            builder = new StringBuilder();

        if (block > 0)
            builder.append(uiStrings.TEXT[2]);
        if (jinx > 0)
            builder.append(uiStrings.TEXT[3].replace("!X1!", String.valueOf(erosion)));
        if (jinx > 0)
            builder.append(uiStrings.TEXT[4].replace("!X2!", String.valueOf(jinx)));
        if (extraDraw == 1)
            builder.append(uiStrings.TEXT[5]);
        else if (extraDraw > 1)
            builder.append(uiStrings.TEXT[6].replace("!X3!", String.valueOf(extraDraw)));
        if (extraEnergy == 1)
            builder.append(uiStrings.TEXT[7]);
        else if (extraEnergy == 2)
            builder.append(uiStrings.TEXT[8]);
        else if (extraEnergy == 3)
            builder.append(uiStrings.TEXT[9]);
        else if (extraEnergy > 3)
            builder.append(uiStrings.TEXT[10].replace("!X4!", String.valueOf(extraEnergy)));

        return builder.toString();
    }

    public int getDamage() {
        int eleCount = damageMods.size();
        int bonus = 0;
        LethalChorusPower pow = null;
        if (adp() != null)
            pow = (LethalChorusPower) adp().getPower(LethalChorusPower.POWER_ID);
        if (pow != null)
            bonus = (amount - 1) * pow.amount;
        return (int)((1 - ELEMENT_DAMAGE_PENALTY*eleCount)*(damage + bonus)/multiHit);
    }

    private String getConciseDescription() {
        boolean newLine = false;
        StringBuilder builder;
        if (multiHit == 1 && damage > 0) {
            builder = new StringBuilder(uiStringsConcise.TEXT[0]);
            newLine = true;
        }
        else if (damage > 0) {
            builder = new StringBuilder(uiStringsConcise.TEXT[1].replace("!X0!", String.valueOf(multiHit)));
            newLine = true;
        }
        else
            builder = new StringBuilder();

        if (block > 0) {
            newLine = addNewLine(newLine, builder);
            builder.append(uiStringsConcise.TEXT[2]);
        }
        if (erosion > 0) {
            newLine = addNewLine(newLine, builder);
            builder.append(uiStringsConcise.TEXT[3].replace("!X1!", String.valueOf(erosion)));
        }
        if (jinx > 0) {
            newLine = addNewLine(newLine, builder);
            builder.append(uiStringsConcise.TEXT[4].replace("!X2!", String.valueOf(jinx)));
        }
        if (extraDraw > 0) {
            newLine = addNewLine(newLine, builder);
            builder.append(uiStringsConcise.TEXT[5].replace("!X3!", String.valueOf(extraDraw)));
        }
        if (extraEnergy > 0) {
            addNewLine(newLine, builder);
            builder.append(uiStringsConcise.TEXT[6].replace("!X4!", String.valueOf(extraEnergy)));
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
        Resonance copy = new Resonance();
        copy.amount = amount;
        copy.damage = damage;
        copy.multiHit = multiHit;
        copy.block = block;
        copy.erosion = erosion;
        copy.jinx = jinx;
        copy.extraDraw = extraDraw;
        copy.extraEnergy = extraEnergy;
        copy.damageMods.addAll(damageMods);
        return copy;
    }
}