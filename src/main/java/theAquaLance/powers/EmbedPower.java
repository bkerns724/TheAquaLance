package theAquaLance.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.AquaLanceMod;
import theAquaLance.cards.AbstractEmbedCard;
import theAquaLance.relics.UnmeltingIce;

import java.util.ArrayList;

import static theAquaLance.util.Wiz.*;

public class EmbedPower extends AbstractEasyPower implements OnReceivePowerPower, OnShufflePowerInterface,
        OnStatusPowerInterface {
    public static final String POWER_ID = AquaLanceMod.makeID("Embedded");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public ArrayList<AbstractEmbedCard> cards;
    public ArrayList<AbstractEmbedCard> popLaterCards;

    public EmbedPower(AbstractCreature owner, AbstractEmbedCard c) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, -1);
        this.name = NAME;
        canGoNegative = false;

        cards = new ArrayList<>();
        popLaterCards = new ArrayList<>();
        cards.add(c);

        updateDescription();
    }

    public void addCard(AbstractEmbedCard c) {
        cards.add(c);
        updateDescription();
    }

    public void popAll() {
        popLaterCards.addAll(cards);

        for (AbstractEmbedCard c : popLaterCards)
            popCard(c);

        popLaterCards.clear();

        att(new RemoveSpecificPowerAction(owner, owner, this));
    }

    public void popCard(AbstractEmbedCard c) {
        c.current_x = owner.hb.cX;
        c.current_y = owner.hb.cY;
        adp().hand.moveToDiscardPile(c);
        cards.remove(c);
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
    public void atStartOfTurn() {
        for (AbstractEmbedCard c: cards)
            c.atStartOfTurn(owner);
    }

    @Override
    public void onShuffle() {
        System.out.println("In Embed Power");
        for (AbstractEmbedCard c : cards) {
            System.out.print("In Card: ");
            System.out.println(c.name);
            c.onShuffle(owner);
        }
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        for (AbstractEmbedCard c: cards)
            c.onApplyPower(owner, power, source, target);
    }

    @Override
    public boolean onApplyStatus(AbstractCreature source, AbstractCard c) {
        for (AbstractEmbedCard car : cards)
            car.onApplyStatus(owner, c);
        return false;
    }

    @Override
    public void onNegatedStatus(AbstractCreature source, AbstractCard c) {
        for (AbstractEmbedCard car : cards)
            car.onApplyStatus(owner, c);
    }

    @Override
    public void onDiscardSigil() {
        for (AbstractEmbedCard c : cards)
            c.onDiscardSigil(owner);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        for (AbstractEmbedCard c : cards)
            c.onAttacked(info.owner, owner, info.type);
        return damageAmount;
    }

    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        for (AbstractEmbedCard c : cards)
            c.onReceivePower(owner, power, target, source);
        return true;
    }

    @Override
    public void update(int slot) {
        super.update(slot);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (cards == null || cards.size() == 0)
            description = powerStrings.DESCRIPTIONS[0];
        else {
            StringBuilder sb = new StringBuilder();
            sb.append(powerStrings.DESCRIPTIONS[1]);
            sb.append(cards.size());

            for (AbstractEmbedCard c : cards) {
                c.applyPowers();
                c.calculateCardDamage((AbstractMonster) owner);
                sb.append(c.getDesc());
            }

            description = sb.toString();
        }
    }
}