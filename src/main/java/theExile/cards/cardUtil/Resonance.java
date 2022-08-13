package theExile.cards.cardUtil;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;
import theExile.cards.AbstractExileCard;
import theExile.cards.AbstractExileCard.elenum;
import theExile.cards.AbstractResonantCard;
import theExile.powers.*;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class Resonance {
    public int amount = 1;
    public int damage = 0;
    public int block = 0;
    public int ringing = 0;
    public int jinx = 0;
    public ArrayList<elenum> damageMods = new ArrayList<>();
    public ArrayList<AbstractExileCard> cards = new ArrayList<>();

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Resonance"));
    private static final UIStrings uiStringsConcise = CardCrawlGame.languagePack.getUIString(makeID("ResonanceConcise"));

    public Resonance() { }

    public void resonanceEffects(AbstractResonantCard card, AbstractMonster m) {
        if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID)) {
            forAllMonstersLiving(mon -> resonanceEffectsSub(card, mon));
            for (AbstractExileCard inCard : cards) {
                inCard.beingDiscarded = true;
                if (inCard.target == AbstractCard.CardTarget.ENEMY)
                    forAllMonstersLiving(mon -> {
                        if (inCard.canUse(adp(), mon)) {
                            inCard.calculateCardDamage(mon);
                            inCard.onUse(adp(), mon);
                        }
                    });
                else if (inCard.target == ExileMod.Enums.AUTOAIM_ENEMY)
                    forAllMonstersLiving(mon -> {
                        if (inCard.canUse(adp(), mon)) {
                            inCard.calculateCardDamage(mon);
                            inCard.onTarget(mon);
                        }
                    });
                else {
                    inCard.calculateCardDamage(m);
                    inCard.onUse(adp(), m);
                }
            }
        }
        else {
            resonanceEffectsSub(card, m);
            for (AbstractExileCard inCard : cards) {
                inCard.beingDiscarded = true;
                if (inCard.canUse(adp(), m)) {
                    inCard.calculateCardDamage(m);
                    inCard.onUse(adp(), m);
                }
            }
        }
    }

    public void resonanceEffectsSub(AbstractResonantCard card, AbstractMonster m) {
        card.baseDamage = getDamage();
        card.baseBlock = block;
        card.calculateCardDamage(m);

        if (card.baseDamage > 0)
            card.dmg(m);
        if (card.baseBlock > 0)
            card.blck();
        if (ringing > 0)
            applyToEnemy(m, new RingingPower(m, ringing));
        if (jinx > 0)
            applyToEnemy(m, new JinxPower(m, jinx));
    }

    public void toPower() {
        Resonance outRes = resClone();
        applyToSelf(new ResonatingPower(outRes));
    }

    public void merge(Resonance inRes) {
        amount += inRes.amount;
        damage += inRes.damage;
        block += inRes.block;
        ringing += inRes.ringing;
        jinx += inRes.jinx;
        cards.addAll(inRes.cards);
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
        if (ringing > 0)
            count += 2;
        if (jinx > 0)
            count++;
        count += cards.size();

        if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID)) {
            if (damage > 0)
                count++;
            if (ringing > 0)
                count ++;
            if (jinx > 0)
                count++;
        }

        if (count > 5)
            return getConciseDescription();

        StringBuilder builder;
        if (damage > 0) {
            if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID))
                builder = new StringBuilder(uiStrings.TEXT[1]);
            else
                builder = new StringBuilder(uiStrings.TEXT[0]);
        }
        else
            builder = new StringBuilder();

        if (block > 0)
            builder.append(uiStrings.TEXT[2]);
        if (ringing > 0) {
            if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID))
                builder.append(uiStrings.TEXT[4].replace("!X1!", String.valueOf(ringing)));
            else
                builder.append(uiStrings.TEXT[3].replace("!X1!", String.valueOf(ringing)));
        }
        if (jinx > 0) {
            if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID))
                builder.append(uiStrings.TEXT[6].replace("!X2!", String.valueOf(jinx)));
            else
                builder.append(uiStrings.TEXT[5].replace("!X2!", String.valueOf(jinx)));
        }
        for (AbstractCard card : cards)
            builder.append(uiStrings.TEXT[7].replace("!CardName!", yellowString(card.name)));

        return builder.toString();
    }

    public boolean isSingleTarget() {
        if (adp() == null)
            return false;
        if (adp().hasPower(AcousticsPower.POWER_ID))
            return false;
        if (damage > 0 || jinx > 0 || ringing > 0)
            return true;
        for (AbstractCard card : cards) {
            if (card.target == AbstractCard.CardTarget.ENEMY)
                return true;
        }
        return false;
    }

    public int getDamage() {
        int bonus = 0;
        SharpNoisePower pow = null;
        if (adp() != null)
            pow = (SharpNoisePower) adp().getPower(SharpNoisePower.POWER_ID);
        if (pow != null)
            bonus = amount * pow.amount;
        return (damage + bonus);
    }

    private String getConciseDescription() {
        StringBuilder builder = new StringBuilder();
        if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID))
            builder.append(uiStringsConcise.TEXT[0]);
        if (damage > 0)
            builder.append(uiStringsConcise.TEXT[1]);

        if (block > 0)
            builder.append(uiStringsConcise.TEXT[2]);

        if (ringing > 0)
            builder.append(uiStringsConcise.TEXT[3].replace("!X1!", String.valueOf(ringing)));
        if (jinx > 0)
            builder.append(uiStringsConcise.TEXT[4].replace("!X2!", String.valueOf(jinx)));

        if (cards.size() < 4)
            for (AbstractCard card : cards)
                builder.append(uiStringsConcise.TEXT[5].replace("!CardName!", yellowString(card.name)));
        else
            builder.append(uiStringsConcise.TEXT[6].replace("!X3!", String.valueOf(cards.size())));

        return builder.toString();
    }

    public static String yellowString(String input) {
        StringBuilder newMsg = new StringBuilder();
        String[] var2 = input.split(" ");

        for (String word : var2) {
            newMsg.append('*').append(word).append(' ');
        }

        return newMsg.toString().trim();
    }

    public Resonance resClone()
    {
        Resonance copy = new Resonance();
        copy.amount = amount;
        copy.damage = damage;
        copy.block = block;
        copy.ringing = ringing;
        copy.jinx = jinx;
        for (AbstractExileCard inCard : cards)
            copy.cards.add((AbstractExileCard)inCard.makeStatEquivalentCopy());
        copy.damageMods.addAll(damageMods);
        return copy;
    }
}