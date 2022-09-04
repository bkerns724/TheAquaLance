package theExile.cards.cardUtil;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.BindingHelper;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
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
import theExile.powers.*;
import theExile.util.Wiz;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.*;
import static theExile.util.Wiz.*;

public class Resonance {
    public int amount = 1;
    public int damage = 0;
    public int block = 0;
    public int ringing = 0;
    public int jinx = 0;
    public int cycle = 0;
    public int charge = 0;
    public ArrayList<AbstractExileCard.elenum> elenums = new ArrayList<>();
    public ArrayList<AbstractExileCard> cards = new ArrayList<>();

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Resonance"));
    private static final UIStrings uiStringsConcise = CardCrawlGame.languagePack.getUIString(makeID("ResonanceConcise"));

    public Resonance() { }

    public void resonanceEffects(AbstractResonantCard card, AbstractMonster m) {
        card.baseDamage = getDamage();
        card.baseBlock = getBlock();
        if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID)) {
            card.setMultiDamage(true);
            card.calculateCardDamage(null);

            if (card.baseDamage > 0) {
                DamageModContainer container = new DamageModContainer(card, getMergedDamageMods(null));
                AttackAction action = new AttackAction(card.multiDamage, Wiz.getAttackEffect(damage, elenums, true));
                BindingHelper.makeAction(container, action);
                atb(action);
            }
            if (getBlock() > 0)
                atb(new GainBlockAction(adp(), card.block));
            forAllMonstersLiving(this::resonanceEffectsSub);
            if (charge > 0)
                applyToSelf(new ChargePower(charge));
            if (cycle > 0) {
                cardDraw(cycle);
                discard(cycle);
            }
            for (AbstractExileCard inCard : cards) {
                inCard.beingDiscarded = true;
                DamageModContainer container = new DamageModContainer(card, getMergedDamageMods(inCard));
                ResonanceUseCardAction action = new ResonanceUseCardAction(inCard, null);
                att(BindingHelper.makeAction(container, action));
            }
        }
        else {
            card.setMultiDamage(false);
            card.calculateCardDamage(m);

            if (card.baseDamage > 0 && m != null) {
                DamageModContainer container = new DamageModContainer(card, getMergedDamageMods(null));
                DamageInfo info = BindingHelper.makeInfo(container, adp(), card.damage, card.damageTypeForTurn);
                AttackAction action = new AttackAction(m, info, Wiz.getAttackEffect(damage, elenums, true));
                atb(action);
            }
            if (getBlock() > 0)
                atb(new GainBlockAction(adp(), card.block));
            resonanceEffectsSub(m);
            if (charge > 0)
                applyToSelf(new ChargePower(charge));
            if (cycle > 0) {
                cardDraw(cycle);
                discard(cycle);
            }
            for (AbstractExileCard inCard : cards) {
                inCard.beingDiscarded = true;
                DamageModContainer container = new DamageModContainer(card, getMergedDamageMods(inCard));
                ResonanceUseCardAction action = new ResonanceUseCardAction(inCard, m);
                att(BindingHelper.makeAction(container, action));
            }
        }
    }

    public void resonanceEffectsSub(AbstractMonster m) {
        if (m == null)
            return;
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
        cycle += inRes.cycle;
        ringing += inRes.ringing;
        jinx += inRes.jinx;
        charge += inRes.charge;
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
        if (getBlock() > 0)
            count++;
        if (ringing > 0)
            count += 2;
        if (jinx > 0)
            count++;
        if (charge > 0)
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

        boolean started = false;

        StringBuilder builder;
        if (getDamage() > 0) {
            if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID))
                builder = new StringBuilder(uiStrings.TEXT[1]);
            else
                builder = new StringBuilder(uiStrings.TEXT[0]);
            started = true;
        }
        else
            builder = new StringBuilder();
        if (getBlock() > 0) {
            if (started)
                builder.append(" NL ");
            started = true;
            builder.append(uiStrings.TEXT[2]);
        }
        if (ringing > 0) {
            if (started)
                builder.append(" NL ");
            started = true;
            if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID))
                builder.append(uiStrings.TEXT[4].replace("!X1!", String.valueOf(ringing)));
            else
                builder.append(uiStrings.TEXT[3].replace("!X1!", String.valueOf(ringing)));
        }
        if (jinx > 0) {
            if (started)
                builder.append(" NL ");
            started = true;
            if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID))
                builder.append(uiStrings.TEXT[6].replace("!X2!", String.valueOf(jinx)));
            else
                builder.append(uiStrings.TEXT[5].replace("!X2!", String.valueOf(jinx)));
        }
        if (charge > 0) {
            if (started)
                builder.append(" NL ");
            started = true;
            builder.append(uiStrings.TEXT[7].replace("!X3!", String.valueOf(charge)));
        }
        if (cycle > 0) {
            if (started)
                builder.append(" NL ");
            started = true;
            if (cycle == 1)
                builder.append(uiStrings.TEXT[8]);
            else
                builder.append(uiStrings.TEXT[9].replace("!X4!", String.valueOf(cycle)));
        }
        for (AbstractCard card : cards) {
            if (started)
                builder.append(" NL ");
            started = true;
            builder.append(uiStrings.TEXT[10].replace("!CardName!", yellowString(card.name)));
        }

        return builder.toString();
    }

    private String getConciseDescription() {
        StringBuilder builder = new StringBuilder();
        int spaceCount = 0;
        if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID)) {
            builder.append(uiStringsConcise.TEXT[0]);
            spaceCount += 2;
        }

        if (getDamage() > 0) {
            builderSpacer(builder, spaceCount);
            spaceCount++;
            builder.append(uiStringsConcise.TEXT[1]);
        }

        if (block > 0) {
            builderSpacer(builder, spaceCount);
            spaceCount++;
            builder.append(uiStringsConcise.TEXT[2]);
        }

        if (ringing > 0) {
            builderSpacer(builder, spaceCount);
            spaceCount++;
            builder.append(uiStringsConcise.TEXT[3].replace("!X1!", String.valueOf(ringing)));
        }
        if (jinx > 0) {
            builderSpacer(builder, spaceCount);
            spaceCount++;
            builder.append(uiStringsConcise.TEXT[4].replace("!X2!", String.valueOf(jinx)));
        }
        if (charge > 0) {
            builderSpacer(builder, spaceCount);
            spaceCount++;
            builder.append(uiStringsConcise.TEXT[5].replace("!X3!", String.valueOf(charge)));
        }
        if (cycle > 0) {
            builderSpacer(builder, spaceCount);
            spaceCount++;
            builder.append(uiStringsConcise.TEXT[6].replace("!X4!", String.valueOf(cycle)));
        }

        if (cards.size() < 3)
            for (AbstractCard card : cards)
                builder.append(uiStringsConcise.TEXT[7].replace("!CardName!", yellowString(card.name)));
        else {
            builderSpacer(builder, spaceCount);
            spaceCount++;
            builder.append(uiStringsConcise.TEXT[8]);
        }

        return builder.toString();
    }

    private void builderSpacer(StringBuilder builder, int spaceCount) {
        if (spaceCount % 2 == 0 && spaceCount != 0)
            builder.append(" NL ");
        else if (spaceCount % 2 == 1)
            builder.append(" ");
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

    public int getBlock() {
        if (block <= 0)
            return -1;
        return block;
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
        copy.block = block;
        copy.ringing = ringing;
        copy.jinx = jinx;
        copy.charge = charge;
        copy.cycle = cycle;
        for (AbstractExileCard inCard : cards)
            copy.cards.add((AbstractExileCard)inCard.makeStatEquivalentCopy());
        copy.elenums.addAll(elenums);
        return copy;
    }
}