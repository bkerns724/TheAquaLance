package theAquaLance.powers;

import basemod.BaseMod;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import theAquaLance.AquaLanceMod;
import theAquaLance.cards.AbstractEmbedCard;

import java.lang.reflect.Method;
import java.util.ArrayList;

import static theAquaLance.util.Wiz.*;

public class EmbedPower extends AbstractEasyPower implements OnReceivePowerPower {
    public static final String POWER_ID = AquaLanceMod.makeID("Embedded");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public ArrayList<AbstractEmbedCard> cards;
    private Method calcMethod;

    public EmbedPower(AbstractCreature owner, AbstractEmbedCard c) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, -1);
        this.name = NAME;
        canGoNegative = false;

        cards = new ArrayList<>();
        cards.add(c);

        try {
            calcMethod = AbstractMonster.class.getDeclaredMethod("calculateDamage", int.class);
        }
        catch (Exception e) {
            return;
        }
        calcMethod.setAccessible(true);

        updateDescription();
    }

    public void addCard(AbstractEmbedCard c) {
        cards.add(c);
        updateDescription();
    }

    public void unEmbed(int popAmount) {
        for (int i = 0; i < popAmount; i++) {
            if (cards.isEmpty())
                break;

            int index = (int)(Math.random() * cards.size());
            popCard(cards.get(index));
            cards.remove(index);
        }

        if (cards.isEmpty())
            att(new RemoveSpecificPowerAction(owner, owner, this));
        else
            updateDescription();
    }

    public void unEmbedAll() {
        for (AbstractEmbedCard c : cards) {
            popCard(c);
        }
        cards.clear();

        att(new RemoveSpecificPowerAction(owner, owner, this));
    }

    public void popCard(AbstractEmbedCard c) {
        int randomDest = MathUtils.random(0, 2);
        c.current_x = owner.hb.cX;
        c.current_y = owner.hb.cY;
        switch (randomDest) {
            case 0:
                if (AbstractDungeon.player.hand.group.size() >= BaseMod.MAX_HAND_SIZE)
                    adp().hand.moveToDiscardPile(c);
                else
                    adp().hand.addToRandomSpot(c);
                break;
            case 1:
                adp().hand.moveToDiscardPile(c);
                break;
            case 2:
                adp().hand.moveToDeck(c, true);
        }
    }

    public void onManualDiscard() {
        if (getDiscardDamAmount() > 0)
            atb(new LoseHPAction(owner, adp(), getDiscardDamAmount()));
    }

    @Override
    public void onDeath() {
        unEmbedAll();
    }

    public void onFinisher(int popAmount) {
        for (AbstractEmbedCard c: cards)
            c.onFinisher(owner);
        unEmbed(popAmount);
    }

    public void onRemove() {
        unEmbedAll();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner == adp() && getLeechAmount() > 0 && info.type == DamageInfo.DamageType.NORMAL)
            att(new GainBlockAction(adp(), getLeechAmount(), true));
        return damageAmount;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        att(new DamageAction(info.owner, new DamageInfo(adp(), getJaggedAmount(), DamageInfo.DamageType.THORNS),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL && getSapAmount() > 0)
            damage -= getSapAmount();
        return damage;
    }

    @Override
    public void atStartOfTurn() {
        if (getDamageAmount() > 0)
            atb(new LoseHPAction(owner, adp(), getDamageAmount()));
        if (getMarkAmount() > 0)
            applyToEnemy((AbstractMonster) owner, new MarkPower(owner, getMarkAmount()));
        if (getFountainAmount() > 0) {
            if (!(owner instanceof AbstractMonster))
                return;
            AbstractMonster fountainSource = (AbstractMonster) owner;
            forAllMonstersLiving(m -> {
                if (m != fountainSource) {
                    int temp = fountainSource.getIntentBaseDmg();
                    try {
                        calcMethod.invoke(fountainSource, getFountainAmount());
                    } catch (Exception e) {
                        return;
                    }
                    DamageInfo enemyDamageInfo = new DamageInfo(fountainSource, fountainSource.getIntentDmg(),
                            DamageInfo.DamageType.NORMAL);
                    atb(new DamageAction(m, enemyDamageInfo, AquaLanceMod.Enums.WATER));
                    fountainSource.setIntentBaseDmg(temp);
                    try {
                        calcMethod.invoke(fountainSource, fountainSource.getIntentBaseDmg());
                    } catch (Exception e) {
                        return;
                    }
                }
            });
        }
    }

    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.type == AbstractPower.PowerType.DEBUFF && source == adp() && source != target && getCatalystAmount() > 0)
            att(new LoseHPAction(target, adp(), getCatalystAmount()));
        return true;
    }

    @Override
    public void updateDescription() {
        if (cards == null || cards.size() == 0)
            description = powerStrings.DESCRIPTIONS[0];
        else {
            StringBuilder sb = new StringBuilder();
            sb.append("Shard Count: ");
            sb.append(cards.size());

            if (getJaggedAmount() > 0)
                sb.append(DESCRIPTIONS[1]).append(getJaggedAmount()).append(DESCRIPTIONS[2]);

            if (getDamageAmount() > 0)
                sb.append(DESCRIPTIONS[3]).append(getDamageAmount()).append(DESCRIPTIONS[4]);

            if (getLeechAmount() > 0)
                sb.append(DESCRIPTIONS[5]).append(getLeechAmount()).append(DESCRIPTIONS[6]);

            if (getDiscardDamAmount() > 0)
                sb.append(DESCRIPTIONS[7]).append(getDiscardDamAmount()).append(DESCRIPTIONS[8]);

            if (getCatalystAmount() > 0)
                sb.append(DESCRIPTIONS[9]).append(getCatalystAmount()).append(DESCRIPTIONS[10]);

            if (getMarkAmount() > 0)
                sb.append(DESCRIPTIONS[11]).append(getMarkAmount()).append(DESCRIPTIONS[12]);

            if (getTriggerAmount() == 1)
                sb.append(DESCRIPTIONS[13]).append(getTriggerAmount()).append(DESCRIPTIONS[15]);
            else if (getTriggerAmount() > 1)
                sb.append(DESCRIPTIONS[13]).append(getTriggerAmount()).append(DESCRIPTIONS[14]);

            if (getFountainAmount() > 0)
                sb.append(DESCRIPTIONS[16]).append(getFountainAmount()).append(DESCRIPTIONS[17]);

            if (getSapAmount() > 0)
                sb.append(DESCRIPTIONS[18]).append(getSapAmount()).append(DESCRIPTIONS[19]);

            description = sb.toString();
        }
    }

    private int getTriggerAmount() {
        int triggerAmount = 0;
        for (AbstractEmbedCard c: cards)
            triggerAmount += c.getTriggerAmount();
        return triggerAmount;
    }

    private int getJaggedAmount() {
        int jaggedAmount = 0;
        for (AbstractEmbedCard c: cards)
            jaggedAmount += c.getJaggedAmount();
        return jaggedAmount;
    }

    private int getCatalystAmount() {
        int catalystAmount = 0;
        for (AbstractEmbedCard c: cards)
            catalystAmount += c.getCatalystAmount();
        return catalystAmount;
    }

    private int getSapAmount() {
        int sapAmount = 0;
        for (AbstractEmbedCard c: cards)
            sapAmount += c.getSapAmount();
        return sapAmount;
    }

    private int getDamageAmount() {
        int damageAmount = 0;
        for (AbstractEmbedCard c: cards)
            damageAmount += c.getDamageAmount();
        return damageAmount;
    }

    private int getDiscardDamAmount() {
        int discardDamAmount = 0;
        for (AbstractEmbedCard c: cards)
            discardDamAmount += c.getDiscardDamAmount();
        return discardDamAmount;
    }

    private int getLeechAmount() {
        int leechAmount = 0;
        for (AbstractEmbedCard c: cards)
            leechAmount += c.getLeechAmount();
        return leechAmount;
    }

    private int getMarkAmount() {
        int markAmount = 0;
        for (AbstractEmbedCard c: cards)
            markAmount += c.getMarkAmount();
        return markAmount;
    }

    public int getFountainAmount() {
        int fountainAmount = 0;
        for (AbstractEmbedCard c: cards)
            fountainAmount += c.getFountainAmount();
        return fountainAmount;
    }
}