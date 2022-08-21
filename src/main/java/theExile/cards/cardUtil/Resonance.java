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
    public int ringing = 0;
    public int jinx = 0;
    public int cycle = 0;
    public ArrayList<elenum> damageMods = new ArrayList<>();
    public ArrayList<AbstractExileCard> cards = new ArrayList<>();

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Resonance"));
    private static final UIStrings uiStringsConcise = CardCrawlGame.languagePack.getUIString(makeID("ResonanceConcise"));

    public Resonance() { }

    public void resonanceEffects(AbstractResonantCard card, AbstractMonster m) {
        if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID)) {
            forAllMonstersLiving(mon -> resonanceEffectsSub(card, mon));
            if (cycle > 0) {
                cardDraw(cycle);
                discard(cycle);
            }
            for (AbstractExileCard inCard : cards) {
                inCard.beingDiscarded = true;
                if (inCard.target == AbstractCard.CardTarget.ENEMY || inCard.target == ExileMod.Enums.AUTOAIM_ENEMY)
                    forAllMonstersLiving(mon -> {
                        if (inCard.canUse(adp(), mon)) {
                            inCard.calculateCardDamage(mon);
                            inCard.onUse(adp(), mon);
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
            if (cycle > 0) {
                cardDraw(cycle);
                discard(cycle);
            }
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
        card.calculateCardDamage(m);

        if (card.baseDamage > 0)
            card.dmg(m);
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
        cycle += inRes.cycle;
        ringing += inRes.ringing;
        jinx += inRes.jinx;
        cards.addAll(inRes.cards);
        for (elenum e : inRes.damageMods )
            if (!damageMods.contains(e))
                damageMods.add(e);
    }

    public String getDescription() {
        int count = 1;
        if (getDamage() > 0)
            count++;
        if (ringing > 0)
            count += 2;
        if (jinx > 0)
            count++;
        if (cycle > 0)
            count += 2;
        count += cards.size();

        if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID)) {
            if (getDamage() > 0)
                count++;
            if (ringing > 0)
                count ++;
            if (jinx > 0)
                count++;
        }

        if (count > 6)
            return getConciseDescription();

        StringBuilder builder;
        if (getDamage() > 0) {
            if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID))
                builder = new StringBuilder(uiStrings.TEXT[1]);
            else
                builder = new StringBuilder(uiStrings.TEXT[0]);
        }
        else
            builder = new StringBuilder();

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
        if (cycle > 0) {
            if (cycle == 1)
                builder.append(uiStrings.TEXT[7]);
            else
                builder.append(uiStrings.TEXT[8].replace("!X3!", String.valueOf(cycle)));
        }
        for (AbstractCard card : cards)
            builder.append(uiStrings.TEXT[9].replace("!CardName!", yellowString(card.name)));

        return builder.toString();
    }

    private String getConciseDescription() {
        StringBuilder builder = new StringBuilder();
        if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID))
            builder.append(uiStringsConcise.TEXT[0]);
        int spaceCount = 0;
        if (getDamage() > 0) {
            builder.append(uiStringsConcise.TEXT[1]);
            spaceCount++;
        }

        if (ringing > 0) {
            if (spaceCount == 1)
                builder.append(" ");
            builder.append(uiStringsConcise.TEXT[3].replace("!X1!", String.valueOf(ringing)));
            spaceCount++;
        }
        if (jinx > 0) {
            if (spaceCount == 2)
                builder.append(" NL ");
            else if (spaceCount == 1)
                builder.append(" ");
            builder.append(uiStringsConcise.TEXT[4].replace("!X2!", String.valueOf(jinx)));
        }
        if (cycle > 0) {
            if (spaceCount == 2)
                builder.append(" NL ");
            else if (spaceCount % 2 == 1)
                builder.append(" ");
            builder.append(uiStringsConcise.TEXT[5].replace("!X3!", String.valueOf(cycle)));
        }

        if (cards.size() < 3)
            for (AbstractCard card : cards)
                builder.append(uiStringsConcise.TEXT[6].replace("!CardName!", yellowString(card.name)));
        else
            builder.append(uiStringsConcise.TEXT[7].replace("!X4!", String.valueOf(cards.size())));

        return builder.toString();
    }

    public AbstractCard.CardType getCardType() {
        if (getDamage() > 0)
            return AbstractCard.CardType.ATTACK;
        for (AbstractExileCard card : cards)
            if (card.type == AbstractCard.CardType.ATTACK)
                return AbstractCard.CardType.ATTACK;
        return AbstractCard.CardType.SKILL;
    }

    public AbstractCard.CardTarget getCardTarget() {
        if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID))
            if (getDamage() > 0 || ringing > 0 || jinx > 0)
                return AbstractCard.CardTarget.ALL_ENEMY;
        if (getDamage() > 0 || ringing > 0 || jinx > 0)
            return AbstractCard.CardTarget.ENEMY;
        for (AbstractExileCard card : cards)
            if (card.target == AbstractCard.CardTarget.ENEMY)
                return AbstractCard.CardTarget.ENEMY;
        for (AbstractExileCard card : cards)
            if (card.target == AbstractCard.CardTarget.ALL_ENEMY || card.target == ExileMod.Enums.AUTOAIM_ENEMY)
                return AbstractCard.CardTarget.ALL_ENEMY;
        return AbstractCard.CardTarget.SELF;
    }

    public int getDamage() {
        if (damage <= 0)
            return -1;
        return damage;
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
        copy.ringing = ringing;
        copy.jinx = jinx;
        copy.cycle = cycle;
        for (AbstractExileCard inCard : cards)
            copy.cards.add((AbstractExileCard)inCard.makeStatEquivalentCopy());
        copy.damageMods.addAll(damageMods);
        return copy;
    }
}