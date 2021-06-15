package theAquaLance.powers;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.AquaLanceMod;
import theAquaLance.cards.AbstractEmbedCard;
import theAquaLance.cards.IceMastery;
import theAquaLance.relics.UnmeltingIce;

import java.util.ArrayList;

import static theAquaLance.util.Wiz.*;

public class EmbedPower extends AbstractEasyPower implements OnReceivePowerPower {
    public static final String POWER_ID = AquaLanceMod.makeID("Embedded");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public ArrayList<AbstractEmbedCard> cards;

    public EmbedPower(AbstractCreature owner, AbstractEmbedCard c) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, -1);
        this.name = NAME;
        canGoNegative = false;

        cards = new ArrayList<>();
        cards.add(c);

        updateDescription();
    }

    public void addCard(AbstractEmbedCard c) {
        cards.add(c);
        updateDescription();
    }

    public void popAll() {
        for (AbstractEmbedCard c : cards) {
            popCard(c);
        }
        cards.clear();

        att(new RemoveSpecificPowerAction(owner, owner, this));
    }

    public void popOne() {
        if (cards.size() > 0)
            popCard(cards.get(0));

        if (cards.isEmpty())
            att(new RemoveSpecificPowerAction(owner, owner, this));
        else
            updateDescription();
    }

    public void popCard(AbstractEmbedCard c) {
        UnmeltingIce relic;
        relic = ((UnmeltingIce) adp().getRelic(UnmeltingIce.ID));
        if (relic != null && !owner.isDeadOrEscaped())
            relic.onTrigger(owner);
        if (!owner.isDeadOrEscaped()) {
            int count = 1;
            if (adp().hasPower(IceMasteryPower.POWER_ID))
                count += adp().getPower(IceMasteryPower.POWER_ID).amount;
            for (int i = 0; i < count; i++)
                c.onPopped(owner);
        }

        int randomDest = AbstractDungeon.miscRng.random(0, 2);
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

    @Override
    public void onDeath() {
        popAll();
    }

    @Override
    public void onRemove() {
        popAll();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        for (AbstractEmbedCard c : cards)
            c.onAttacked(info.owner);
        return damageAmount;
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        float modifier = 100F;
        for (AbstractEmbedCard c : cards)
            modifier += c.getDamageModifier();
        damage *= modifier/100.0F;
        return damage;
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        for (AbstractEmbedCard c : cards)
            damage = c.atDamageGive(damage, type);
        return damage;
    }

    @Override
    public void atStartOfTurn() {
        for (AbstractEmbedCard c: cards)
            c.atStartOfTurn(this);
    }

    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        for (AbstractEmbedCard c : cards)
            c.onReceivePower(power, target, source);
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

            description = sb.toString();
        }
    }
}