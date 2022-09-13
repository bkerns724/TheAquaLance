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
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theExile.ExileMod;
import theExile.actions.AttackAction;
import theExile.actions.ResonanceUseCardAction;
import theExile.cards.AbstractExileCard;
import theExile.cards.AbstractExileCard.elenum;
import theExile.cards.AbstractResonantCard;
import theExile.damagemods.DeathLightningDamage;
import theExile.damagemods.EldritchDamage;
import theExile.damagemods.PhantasmalDamage;
import theExile.damagemods.IceDamage;
import theExile.powers.*;
import theExile.util.Wiz;

import java.util.ArrayList;
import java.util.Collections;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.*;
import static theExile.util.Wiz.*;

public class Resonance {
    public int amount = 1;
    public int damage = 0;
    public int block = 0;
    public int ringing = 0;
    public int jinx = 0;
    public int vigor = 0;
    public int draw = 0;
    public int discard = 0;
    public int multi = 1;
    public ArrayList<AbstractExileCard.elenum> elenums = new ArrayList<>();
    public ArrayList<AbstractExileCard> cards = new ArrayList<>();

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Resonance"));
    private static final UIStrings uiStringsConcise = CardCrawlGame.languagePack.getUIString(makeID("ResonanceConcise"));

    public Resonance() { }

    public void resonanceEffects(AbstractResonantCard card, AbstractMonster m) {
        card.baseDamage = getDamage();
        card.baseBlock = getBlock();
        if (getBlock() > 0)
            atb(new GainBlockAction(adp(), card.block));
        if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID)) {
            if (card.baseDamage > 0) {
                DamageModContainer container = new DamageModContainer(card, getMergedDamageMods(null));
                for (int i = 0; i < multi; i++) {
                    AttackAction action = new AttackAction(card.multiDamage,
                            Wiz.getAttackEffect(card.getDamageForVFX(), elenums, true));
                    BindingHelper.makeAction(container, action);
                    atb(action);
                }
            }
            forAllMonstersLiving(this::resonanceEffectsSub);
            if (vigor > 0)
                applyToSelf(new VigorPower(adp(), vigor));
            if (draw > 0)
                Wiz.cardDraw(draw);
            if (discard > 0)
                Wiz.discard(discard);

            Collections.reverse(cards);
            for (AbstractExileCard inCard : cards) {
                inCard.freeToPlayOnce = true;
                ResonanceUseCardAction action = new ResonanceUseCardAction(inCard, null);
                BindingHelper.bindAction(getMergedDamageMods(inCard), action);
                att(action);
            }
            Collections.reverse(cards);
        }
        else {
            if (getBlock() > 0)
                atb(new GainBlockAction(adp(), card.block));
            if (card.baseDamage > 0 && m != null) {
                DamageModContainer container = new DamageModContainer(card, getMergedDamageMods(null));
                DamageInfo info = BindingHelper.makeInfo(container, adp(), card.damage, card.damageTypeForTurn);
                for (int i = 0; i < multi; i++) {
                    AttackAction action = new AttackAction(m, info, Wiz.getAttackEffect(card.damage, elenums, true));
                    atb(action);
                }
            }
            resonanceEffectsSub(m);
            if (vigor > 0)
                applyToSelf(new VigorPower(adp(), vigor));
            if (draw > 0)
                Wiz.cardDraw(draw);
            if (discard > 0)
                Wiz.discard(discard);

            Collections.reverse(cards);
            for (AbstractExileCard inCard : cards) {
                inCard.freeToPlayOnce = true;
                ResonanceUseCardAction action = new ResonanceUseCardAction(inCard, m);
                BindingHelper.bindAction(getMergedDamageMods(inCard), action);
                att(action);
            }
            Collections.reverse(cards);
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
        vigor += inRes.vigor;
        draw += inRes.draw;
        discard += inRes.discard;
        ringing += inRes.ringing;
        jinx += inRes.jinx;
        multi += inRes.multi - 1;
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
        if (eList.contains(PHANTASMAL))
            outList.add(new PhantasmalDamage(false));
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
            count += 1;
        if (jinx > 0)
            count++;
        if (vigor > 0)
            count ++;
        if (draw > 0)
            count ++;
        if (discard > 0)
            count ++;
        count += cards.size();

        if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID)) {
            if (multi > 1)
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
            if (multi == 1) {
                if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID))
                    builder = new StringBuilder(uiStrings.TEXT[1]);
                else
                    builder = new StringBuilder(uiStrings.TEXT[0]);
            }
            else {
                if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID))
                    builder = new StringBuilder(uiStrings.TEXT[3].replace("!X1!", String.valueOf(multi)));
                else
                    builder = new StringBuilder(uiStrings.TEXT[2].replace("!X1!", String.valueOf(multi)));

            }
            started = true;
        }
        else
            builder = new StringBuilder();
        if (getBlock() > 0) {
            if (started)
                builder.append(" NL ");
            started = true;
            builder.append(uiStrings.TEXT[4]);
        }
        if (ringing > 0) {
            if (started)
                builder.append(" NL ");
            started = true;
            if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID))
                builder.append(uiStrings.TEXT[6].replace("!X2!", String.valueOf(ringing)));
            else
                builder.append(uiStrings.TEXT[5].replace("!X2!", String.valueOf(ringing)));
        }
        if (jinx > 0) {
            if (started)
                builder.append(" NL ");
            started = true;
            if (adp() != null && adp().hasPower(AcousticsPower.POWER_ID))
                builder.append(uiStrings.TEXT[8].replace("!X3!", String.valueOf(jinx)));
            else
                builder.append(uiStrings.TEXT[7].replace("!X3!", String.valueOf(jinx)));
        }
        if (vigor > 0) {
            if (started)
                builder.append(" NL ");
            started = true;
            builder.append(uiStrings.TEXT[9].replace("!X4!", String.valueOf(vigor)));
        }
        if (draw > 0) {
            if (started)
                builder.append(" NL ");
            started = true;
            if (draw == 1)
                builder.append(uiStrings.TEXT[10]);
            else
                builder.append(uiStrings.TEXT[11].replace("!X5!", String.valueOf(draw)));
        }
        if (discard > 0) {
            if (started)
                builder.append(" NL ");
            started = true;
            if (discard == 1)
                builder.append(uiStrings.TEXT[12]);
            else
                builder.append(uiStrings.TEXT[13].replace("!X6!", String.valueOf(discard)));
        }
        for (AbstractCard card : cards) {
            if (started)
                builder.append(" NL ");
            started = true;
            builder.append(uiStrings.TEXT[14].replace("!CardName!", yellowString(card.name)));
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
            if (multi == 1) {
                builderSpacer(builder, spaceCount);
                spaceCount++;
                builder.append(uiStringsConcise.TEXT[1]);
            }
            else {
                builderSpacer(builder, spaceCount);
                spaceCount += 2;
                builder.append(uiStringsConcise.TEXT[2].replace("!X1!", String.valueOf(multi)));
            }
        }

        if (block > 0) {
            builderSpacer(builder, spaceCount);
            spaceCount++;
            builder.append(uiStringsConcise.TEXT[3]);
        }

        if (ringing > 0) {
            builderSpacer(builder, spaceCount);
            spaceCount++;
            builder.append(uiStringsConcise.TEXT[4].replace("!X2!", String.valueOf(ringing)));
        }
        if (jinx > 0) {
            builderSpacer(builder, spaceCount);
            spaceCount++;
            builder.append(uiStringsConcise.TEXT[5].replace("!X3!", String.valueOf(jinx)));
        }
        if (vigor > 0) {
            builderSpacer(builder, spaceCount);
            spaceCount++;
            builder.append(uiStringsConcise.TEXT[6].replace("!X4!", String.valueOf(vigor)));
        }
        if (draw > 0) {
            builderSpacer(builder, spaceCount);
            spaceCount++;
            builder.append(uiStringsConcise.TEXT[7].replace("!X5!", String.valueOf(draw)));
        }
        if (discard > 0) {
            builderSpacer(builder, spaceCount);
            spaceCount++;
            builder.append(uiStringsConcise.TEXT[8].replace("!X6!", String.valueOf(discard)));
        }
        if (cards.size() > 0) {
            builderSpacer(builder, spaceCount);
            spaceCount++;
            builder.append(uiStringsConcise.TEXT[9]);
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
        return (damage / multi);
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
        copy.multi = multi;
        copy.vigor = vigor;
        copy.draw = draw;
        copy.discard = discard;
        for (AbstractExileCard inCard : cards)
            copy.cards.add((AbstractExileCard)inCard.makeStatEquivalentCopy());
        copy.elenums.addAll(elenums);
        return copy;
    }
}