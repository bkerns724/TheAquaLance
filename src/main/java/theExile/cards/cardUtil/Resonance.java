package theExile.cards.cardUtil;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.BindingHelper;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theExile.ExileMod;
import theExile.actions.AttackAction;
import theExile.actions.ResonanceUseCardAction;
import theExile.cards.AbstractExileCard;
import theExile.cards.AbstractExileCard.elenum;
import theExile.cards.AbstractResonantCard;
import theExile.damagemods.DeathLightningDamage;
import theExile.damagemods.EldritchDamage;
import theExile.damagemods.ForceDamage;
import theExile.damagemods.IceDamage;
import theExile.powers.AcousticsPower;
import theExile.powers.JinxPower;
import theExile.powers.ResonatingPower;
import theExile.powers.RingingPower;
import theExile.util.Wiz;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.*;
import static theExile.util.Wiz.*;

public class Resonance {
    public int amount = 1;
    public int damage = 0;
    public int ringing = 0;
    public int jinx = 0;
    public int cycle = 0;
    public int vigor = 0;
    public ArrayList<AbstractExileCard.elenum> elenums = new ArrayList<>();
    public ArrayList<AbstractExileCard> cards = new ArrayList<>();

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Resonance"));
    private static final UIStrings uiStringsConcise = CardCrawlGame.languagePack.getUIString(makeID("ResonanceConcise"));

    public Resonance() { }

    public void resonanceEffects(AbstractResonantCard card, AbstractMonster m) {
        if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID)) {
            card.setMultiDamage(true);
            card.baseDamage = getDamage();
            card.calculateCardDamage(null);

            if (card.baseDamage > 0) {
                DamageModContainer container = new DamageModContainer(this, getMergedDamageMods(null));
                DamageInfo info = new DamageInfo(adp(), card.damage, card.damageTypeForTurn);
                AttackAction action = new AttackAction(m, info, Wiz.getAttackEffect(damage, elenums, true));
                atb(BindingHelper.makeAction(container, action));
            }
            forAllMonstersLiving(mon -> resonanceEffectsSub(card, mon));
            if (vigor > 0)
                applyToSelf(new VigorPower(adp(), vigor));
            if (cycle > 0) {
                cardDraw(cycle);
                discard(cycle);
            }
            for (AbstractExileCard inCard : cards) {
                inCard.beingDiscarded = true;
                DamageModContainer container = new DamageModContainer(this, getMergedDamageMods(inCard));
                ResonanceUseCardAction action = new ResonanceUseCardAction(card, null);
                att(BindingHelper.makeAction(container, action));
            }
        }
        else {
            card.setMultiDamage(false);
            card.baseDamage = getDamage();
            card.calculateCardDamage(m);

            if (card.baseDamage > 0) {
                DamageModContainer container = new DamageModContainer(this, getMergedDamageMods(null));
                DamageInfo info = new DamageInfo(adp(), card.damage, card.damageTypeForTurn);
                AttackAction action = new AttackAction(m, info, Wiz.getAttackEffect(damage, elenums, true));
                atb(BindingHelper.makeAction(container, action));
            }
            resonanceEffectsSub(card, m);
            if (vigor > 0)
                applyToSelf(new VigorPower(adp(), vigor));
            if (cycle > 0) {
                cardDraw(cycle);
                discard(cycle);
            }
            for (AbstractExileCard inCard : cards) {
                inCard.beingDiscarded = true;
                DamageModContainer container = new DamageModContainer(this, getMergedDamageMods(inCard));
                ResonanceUseCardAction action = new ResonanceUseCardAction(card, m);
                att(BindingHelper.makeAction(container, action));
            }
        }
    }

    public void resonanceEffectsSub(AbstractResonantCard card, AbstractMonster m) {
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
        vigor += inRes.vigor;
        cards.addAll(inRes.cards);
        for (AbstractExileCard.elenum e : inRes.elenums )
            if (!elenums.contains(e))
                elenums.add(e);
    }

    private ArrayList<AbstractDamageModifier> getMergedDamageMods(AbstractExileCard card) {
        ArrayList<elenum> eList = new ArrayList<>(elenums);
        if (card != null) {
            for (elenum e : card.damageModList) {
                if (!eList.contains(e))
                    eList.add(e);
            }
        }

        ArrayList<AbstractDamageModifier> outList = new ArrayList<>();

        if (eList.contains(ICE))
            outList.add(new IceDamage(false));
        if (eList.contains(LIGHTNING))
            outList.add(new DeathLightningDamage(false));
        if (eList.contains(FORCE))
            outList.add(new ForceDamage(false));
        if (eList.contains(ELDRITCH))
            outList.add(new EldritchDamage(false));

        return outList;
    }

    public String getDescription() {
        int count = 1;
        if (getDamage() > 0)
            count++;
        if (ringing > 0)
            count += 2;
        if (jinx > 0)
            count++;
        if (vigor > 0)
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
                builder.append(uiStrings.TEXT[3].replace("!X1!", String.valueOf(ringing)));
            else
                builder.append(uiStrings.TEXT[2].replace("!X1!", String.valueOf(ringing)));
        }
        if (jinx > 0) {
            if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID))
                builder.append(uiStrings.TEXT[5].replace("!X2!", String.valueOf(jinx)));
            else
                builder.append(uiStrings.TEXT[4].replace("!X2!", String.valueOf(jinx)));
        }
        if (vigor > 0) {
            if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID))
                builder.append(uiStrings.TEXT[6].replace("!X3!", String.valueOf(jinx)));
        }
        if (cycle > 0) {
            if (cycle == 1)
                builder.append(uiStrings.TEXT[7]);
            else
                builder.append(uiStrings.TEXT[8].replace("!X4!", String.valueOf(cycle)));
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
            builder.append(uiStringsConcise.TEXT[2].replace("!X1!", String.valueOf(ringing)));
            spaceCount++;
        }
        if (jinx > 0) {
            if (spaceCount == 2)
                builder.append(" NL ");
            else if (spaceCount == 1)
                builder.append(" ");
            builder.append(uiStringsConcise.TEXT[3].replace("!X2!", String.valueOf(jinx)));
            spaceCount++;
        }
        if (vigor > 0) {
            if (spaceCount == 2)
                builder.append(" NL ");
            else if (spaceCount == 1)
                builder.append(" ");
            builder.append(uiStringsConcise.TEXT[4].replace("!X3!", String.valueOf(jinx)));
            spaceCount++;
        }
        if (cycle > 0) {
            if (spaceCount == 2 || spaceCount == 4)
                builder.append(" NL ");
            else if (spaceCount % 2 == 1)
                builder.append(" ");
            builder.append(uiStringsConcise.TEXT[5].replace("!X4!", String.valueOf(cycle)));
        }

        if (cards.size() < 3)
            for (AbstractCard card : cards)
                builder.append(uiStringsConcise.TEXT[6].replace("!CardName!", yellowString(card.name)));
        else
            builder.append(uiStringsConcise.TEXT[7]);

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
        if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID)) {
            if (getDamage() > 0 || ringing > 0 || jinx > 0)
                return AbstractCard.CardTarget.ALL_ENEMY;
            for (AbstractExileCard card : cards)
                if (card.target == AbstractCard.CardTarget.ALL_ENEMY || card.target == ExileMod.Enums.AUTOAIM_ENEMY
                        || card.target == AbstractCard.CardTarget.ENEMY )
                    return AbstractCard.CardTarget.ALL_ENEMY;
            return AbstractCard.CardTarget.SELF;
        }
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

    public void addModifier(AbstractExileCard.elenum e) {
        if (!elenums.contains(e))
            elenums.add(e);
    }

    public Resonance resClone()
    {
        Resonance copy = new Resonance();
        copy.amount = amount;
        copy.damage = damage;
        copy.ringing = ringing;
        copy.jinx = jinx;
        copy.vigor = vigor;
        copy.cycle = cycle;
        for (AbstractExileCard inCard : cards)
            copy.cards.add((AbstractExileCard)inCard.makeStatEquivalentCopy());
        copy.elenums.addAll(elenums);
        return copy;
    }
}