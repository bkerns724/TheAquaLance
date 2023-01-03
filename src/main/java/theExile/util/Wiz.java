package theExile.util;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import theExile.ExileMod;
import theExile.actions.TimedVFXAction;
import theExile.cards.AbstractExileCard;
import theExile.cards.AbstractExileCard.elenum;
import theExile.powers.JinxPower;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static theExile.cards.AbstractExileCard.elenum.*;

public class Wiz {
    public static final int DAMAGE_THRESHOLD_M = 15;
    public static final int DAMAGE_THRESHOLD_L = 40;
    // Thanks for the shortcuts Vex
    public static AbstractPlayer adp() {
        return AbstractDungeon.player;
    }

    public static AbstractGameAction.AttackEffect getAttackEffect(int damage, ArrayList<elenum> damageModListInput, boolean resonant) {
        ArrayList<elenum> damageModList = new ArrayList<>(damageModListInput);

        if (damageModList.size() == 1) {
            AbstractExileCard.elenum ele = damageModList.get(0);
            if (ele == ICE)
                return getIceEffect(damage);
            else if (ele == FORCE)
                return getForceEffect(damage);
            else if (ele == ELDRITCH)
                return getEldritchEffect(damage);
            else if (ele == LIGHTNING)
                return getLightningEffect(damage);
            else
                return getBluntEffect(damage);
        } else if (damageModList.size() > 1)
            return getDarkWaveEffect(damage);
        else if (resonant)
            return getResonantEffect(damage);

        int x = MathUtils.random(0, 1);
        if (x == 1)
            return getSlashEffect(damage);
        return getBluntEffect(damage);
    }

    public static AbstractGameAction.AttackEffect getRandomSlash() {
        int x = AbstractDungeon.miscRng.random(0, 2);
        if (x == 0)
            return AbstractGameAction.AttackEffect.SLASH_DIAGONAL;
        if (x == 1)
            return AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;
        return AbstractGameAction.AttackEffect.SLASH_VERTICAL;
    }

    public static AbstractGameAction.AttackEffect getSlashEffect(int damage) {
        if (damage < DAMAGE_THRESHOLD_M)
            return getRandomSlash();
        else if (damage < DAMAGE_THRESHOLD_L)
            return AbstractGameAction.AttackEffect.SLASH_HEAVY;
        return ExileMod.Enums.SLASH_MASSIVE;
    }

    public static AbstractGameAction.AttackEffect getBluntEffect(int damage) {
        if (damage < DAMAGE_THRESHOLD_M)
            return AbstractGameAction.AttackEffect.BLUNT_LIGHT;
        else if (damage < DAMAGE_THRESHOLD_L)
            return AbstractGameAction.AttackEffect.BLUNT_HEAVY;
        return ExileMod.Enums.BLUNT_MASSIVE;
    }

    public static AbstractGameAction.AttackEffect getFireEffect(int damage) {
        if (damage < DAMAGE_THRESHOLD_M)
            return AbstractGameAction.AttackEffect.FIRE;
        else if (damage < DAMAGE_THRESHOLD_L)
            return ExileMod.Enums.FIRE_M;
        else
            return ExileMod.Enums.FIRE_L;
    }

    public static AbstractGameAction.AttackEffect getAcidEffect(int damage) {
        if (damage < DAMAGE_THRESHOLD_M)
            return ExileMod.Enums.ACID;
        else if (damage < DAMAGE_THRESHOLD_L)
            return ExileMod.Enums.ACID_M;
        else
            return ExileMod.Enums.ACID_L;
    }

    public static AbstractGameAction.AttackEffect getIceEffect(int damage) {
        if (damage < DAMAGE_THRESHOLD_M)
            return ExileMod.Enums.ICE;
        else if (damage < DAMAGE_THRESHOLD_L)
            return ExileMod.Enums.ICE_M;
        else
            return ExileMod.Enums.ICE_L;
    }

    public static AbstractGameAction.AttackEffect getForceEffect(int damage) {
        if (damage < DAMAGE_THRESHOLD_M)
            return ExileMod.Enums.FORCE;
        else if (damage < DAMAGE_THRESHOLD_L)
            return ExileMod.Enums.FORCE_M;
        else
            return ExileMod.Enums.FORCE_L;
    }

    public static AbstractGameAction.AttackEffect getLightningEffect(int damage) {
        if (damage < DAMAGE_THRESHOLD_M)
            return ExileMod.Enums.LIGHTNING_S;
        else if (damage < DAMAGE_THRESHOLD_L)
            return ExileMod.Enums.LIGHTNING_M;
        else
            return ExileMod.Enums.LIGHTNING_L;
    }

    public static AbstractGameAction.AttackEffect getEldritchEffect(int damage) {
        if (damage < DAMAGE_THRESHOLD_M)
            return ExileMod.Enums.ELDRITCH;
        else if (damage < DAMAGE_THRESHOLD_L)
            return ExileMod.Enums.ELDRITCH_M;
        else
            return ExileMod.Enums.ELDRITCH_L;
    }

    public static AbstractGameAction.AttackEffect getResonantEffect(int damage) {
        if (damage < DAMAGE_THRESHOLD_M)
            return ExileMod.Enums.RESONANT;
        else if (damage < DAMAGE_THRESHOLD_L)
            return ExileMod.Enums.RESONANT_M;
        else
            return ExileMod.Enums.RESONANT_L;
    }

    public static AbstractGameAction.AttackEffect getDarkWaveEffect(int damage) {
        if (damage < DAMAGE_THRESHOLD_M)
            return ExileMod.Enums.DARK_WAVE;
        else if (damage < DAMAGE_THRESHOLD_L)
            return ExileMod.Enums.DARK_WAVE_M;
        else
            return ExileMod.Enums.DARK_WAVE_L;
    }

    public static void forAllCardsInList(ArrayList<AbstractCard> cardsList, Consumer<AbstractCard> consumer) {
        for (AbstractCard c : cardsList) {
            consumer.accept(c);
        }
    }

    public static void thornDmg(AbstractCreature m, int amount, AbstractGameAction.AttackEffect AtkFX) {
        atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, amount, DamageInfo.DamageType.THORNS), AtkFX));
    }

    public static void thornDmg(AbstractCreature m, int amount) {
        thornDmg(m, amount, AbstractGameAction.AttackEffect.NONE);
    }

    public static void thornDmgAll(int amount, AbstractGameAction.AttackEffect effect) {
        forAllMonstersLiving(mon -> thornDmg(mon, amount, effect));
    }

    public static void thornDmgTop(AbstractCreature m, int amount, AbstractGameAction.AttackEffect AtkFX) {
        att(new DamageAction(m, new DamageInfo(AbstractDungeon.player, amount, DamageInfo.DamageType.THORNS), AtkFX));
    }

    public static void thornDmgTop(AbstractCreature m, int amount, AbstractGameAction.AttackEffect AtkFX, boolean superfast) {
        att(new DamageAction(m, new DamageInfo(AbstractDungeon.player, amount, DamageInfo.DamageType.THORNS), AtkFX, superfast));
    }

    public static void thornDmgTop(AbstractCreature m, int amount) {
        thornDmgTop(m, amount, AbstractGameAction.AttackEffect.NONE);
    }

    public static void cDraw(int amount) { atb(new DrawCardAction(amount)); }

    public static void discard(int amount, boolean isRandom) {
        atb(new DiscardAction(adp(), adp(), amount, isRandom));
    }

    public static void discard(int amount) {
        discard(amount, false);
    }

    public static void discardTop(int amount, boolean isRandom) {
        att(new DiscardAction(adp(), adp(), amount, isRandom));
    }

    public static void discardTop(int amount) {
        discardTop(amount, false);
    }

    public static void removePower(AbstractPower pow) {
        atb(new RemoveSpecificPowerAction(pow.owner, pow.owner, pow));
    }

    public static AbstractRelic randRelic() {
        return AbstractDungeon.returnRandomRelic(AbstractDungeon.returnRandomRelicTier());
    }

    public static AbstractRoom adRoom() {
        if (AbstractDungeon.getCurrMapNode() != null)
            return AbstractDungeon.getCurrRoom();
        else
            return null;
    }

    public static ArrayList<AbstractCard> getAllCardsInCardGroups(boolean includeHand, boolean includeExhaust) {
        ArrayList<AbstractCard> masterCardsList = new ArrayList<>();
        masterCardsList.addAll(AbstractDungeon.player.drawPile.group);
        masterCardsList.addAll(AbstractDungeon.player.discardPile.group);
        if (includeHand) {
            masterCardsList.addAll(AbstractDungeon.player.hand.group);
        }
        if (includeExhaust) {
            masterCardsList.addAll(AbstractDungeon.player.exhaustPile.group);
        }
        return masterCardsList;
    }

    public static void forAllMonstersLiving(Consumer<AbstractMonster> consumer) {
        for (AbstractMonster m : getEnemies()) {
            consumer.accept(m);
        }
    }

    public static ArrayList<AbstractMonster> getEnemies() {
        ArrayList<AbstractMonster> monsters = new ArrayList<>(AbstractDungeon.getMonsters().monsters);
        monsters.removeIf(m -> m.isDead || m.isDying || m.halfDead);
        return monsters;
    }

    public static AbstractMonster getLowestHealthEnemy() {
        AbstractMonster weakest = null;
        for (AbstractMonster m : getEnemies()) {
            if (weakest == null)
                weakest = m;
            else if (weakest.currentHealth > m.currentHealth)
                weakest = m;
        }
        return weakest;
    }

    public static AbstractMonster getHighestHealthEnemy() {
        AbstractMonster strongest = null;
        for (AbstractMonster m : getEnemies()) {
            if (strongest == null)
                strongest = m;
            else if (strongest.currentHealth < m.currentHealth)
                strongest = m;
        }
        return strongest;
    }

    public static AbstractMonster getRandomEnemy() {
        return AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
    }

    public static ArrayList<AbstractCard> getCardsMatchingPredicate(Predicate<AbstractCard> pred) {
        return getCardsMatchingPredicate(pred, false);
    }

    public static ArrayList<AbstractCard> getCardsMatchingPredicate(Predicate<AbstractCard> pred, boolean allcards) {
        if (allcards) {
            ArrayList<AbstractCard> cardsList = new ArrayList<>();
            for (AbstractCard c : CardLibrary.getAllCards()) {
                if (pred.test(c)) cardsList.add(c.makeStatEquivalentCopy());
            }
            return cardsList;
        } else {
            ArrayList<AbstractCard> cardsList = new ArrayList<>();
            for (AbstractCard c : AbstractDungeon.srcCommonCardPool.group) {
                if (pred.test(c)) cardsList.add(c.makeStatEquivalentCopy());
            }
            for (AbstractCard c : AbstractDungeon.srcUncommonCardPool.group) {
                if (pred.test(c)) cardsList.add(c.makeStatEquivalentCopy());
            }
            for (AbstractCard c : AbstractDungeon.srcRareCardPool.group) {
                if (pred.test(c)) cardsList.add(c.makeStatEquivalentCopy());
            }
            return cardsList;
        }
    }

    public static AbstractCard returnTrulyRandomPrediCardInCombat(Predicate<AbstractCard> pred, boolean allCards) {
        return getRandomItem(getCardsMatchingPredicate(pred, allCards));
    }

    public static AbstractCard returnTrulyRandomPrediCardInCombat(Predicate<AbstractCard> pred) {
        return returnTrulyRandomPrediCardInCombat(pred, false);
    }

    public static <T> T getRandomItem(ArrayList<T> list, Random rng) {
        return list.isEmpty() ? null : list.get(rng.random(list.size() - 1));
    }

    public static <T> T getRandomItem(ArrayList<T> list) {
        return getRandomItem(list, AbstractDungeon.cardRandomRng);
    }

    private static boolean actuallyHovered(Hitbox hb) {
        return InputHelper.mX > hb.x && InputHelper.mX < hb.x + hb.width && InputHelper.mY > hb.y && InputHelper.mY < hb.y + hb.height;
    }

    public static boolean isInCombat() {
        return CardCrawlGame.isInARun() && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT;
    }

    public static void atb(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }

    public static void att(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToTop(action);
    }

    public static void vfx(AbstractGameEffect gameEffect) {
        atb(new VFXAction(gameEffect));
    }

    public static void vfx(AbstractGameEffect gameEffect, float duration) {
        atb(new VFXAction(gameEffect, duration));
    }

    public static void vfxTop(AbstractGameEffect gameEffect, float duration) {
        att(new VFXAction(gameEffect, duration));
    }

    public static void vfxTopFront(AbstractGameEffect gameEffect, float duration) {
        att(new VFXAction(adp(), gameEffect, duration, true));
    }

    public static void vfxTop(AbstractGameEffect gameEffect) {
        att(new VFXAction(gameEffect));
    }

    public static void tfx(AbstractGameEffect gameEffect) {
        atb(new TimedVFXAction(gameEffect));
    }

    public static void makeInHand(AbstractCard c, int i) {
        atb(new MakeTempCardInHandAction(c, i));
    }

    public static void makeInHand(AbstractCard c) {
        makeInHand(c, 1);
    }

    public static void shuffleIn(AbstractCard c, int i) {
        atb(new MakeTempCardInDrawPileAction(c, i, true, true));
    }

    public static void shuffleIn(AbstractCard c) {
        shuffleIn(c, 1);
    }

    public static void topDeck(AbstractCard c, int i) {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, i, false, true));
    }

    public static void topDeck(AbstractCard c) {
        topDeck(c, 1);
    }

    public static void applyToEnemy(AbstractMonster m, AbstractPower po) {
        atb(new ApplyPowerAction(m, adp(), po, po.amount));
    }

    public static void applyToEnemy(AbstractCreature m, AbstractPower po) {
        atb(new ApplyPowerAction(m, adp(), po, po.amount));
    }

    public static void applyToEnemy(AbstractMonster m, AbstractPower po, AbstractGameAction.AttackEffect effect) {
        atb(new ApplyPowerAction(m, adp(), po, po.amount, effect));
    }

    public static void applyToEnemy(AbstractCreature m, AbstractPower po, AbstractGameAction.AttackEffect effect) {
        atb(new ApplyPowerAction(m, adp(), po, po.amount, effect));
    }

    public static void applyToEnemyTop(AbstractCreature m, AbstractPower po) {
        att(new ApplyPowerAction(m, AbstractDungeon.player, po, po.amount));
    }

    public static void applyToEnemyTopFast(AbstractCreature m, AbstractPower po) {
        att(new ApplyPowerAction(m, AbstractDungeon.player, po, po.amount, true));
    }

    public static void applyToSelf(AbstractPower po) {
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount));
    }

    public static void applyToSelfTop(AbstractPower po) {
        att(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount));
    }

    public static int getDebuffCount(AbstractCreature m) {
        if (m == null || m.powers == null)
            return 0;
        int debuffCount = 0;
        for (AbstractPower p : m.powers) {
            if (p != null && p.type == AbstractPower.PowerType.DEBUFF && !(p instanceof GainStrengthPower))
                debuffCount++;
        }
        return debuffCount;
    }

    public static int getJinxAmount(AbstractCreature m) {
        if (m == null || !m.hasPower(JinxPower.POWER_ID))
            return 0;
        return m.getPower(JinxPower.POWER_ID).amount;
    }

    public static String replaceLast(String string, String from, String to) {
        int lastIndex = string.lastIndexOf(from);
        if (lastIndex < 0)
            return string;
        String tail = string.substring(lastIndex).replaceFirst(from, to);
        return string.substring(0, lastIndex) + tail;
    }
}
