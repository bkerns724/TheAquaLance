package theExile.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theExile.ExileMod;
import theExile.cards.cardUtil.Resonance;
import theExile.powers.ResonantCostZeroPower;

import static theExile.util.Wiz.adRoom;
import static theExile.util.Wiz.adp;

public abstract class AbstractResonantCard extends AbstractExileCard {
    public Resonance resonance;

    public AbstractResonantCard(String id, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(id, cost, type, rarity, target);
        resonance = new Resonance();
        setResonance();
        initializeDescription();
    }

    protected abstract void setResonance();

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        resonance.resonanceEffects(this, m);
    }

    @Override
    public boolean freeToPlay() {
        if (super.freeToPlay())
            return true;

        if (adp() != null && AbstractDungeon.currMapNode != null &&
                adRoom() != null && adRoom().phase == AbstractRoom.RoomPhase.COMBAT &&
                adp().hasPower(ResonantCostZeroPower.POWER_ID))
            return true;

        return false;
    }

    @Override
    public void applyPowers() {
        baseDamage = resonance.getDamage();
        type = resonance.getCardType();
        target = resonance.getCardTarget();
        if (target == CardTarget.ALL_ENEMY || target == ExileMod.Enums.AUTOAIM_ENEMY)
            isMultiDamage = true;
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        baseDamage = resonance.getDamage();
        type = resonance.getCardType();
        target = resonance.getCardTarget();
        if (target == CardTarget.ALL_ENEMY || target == ExileMod.Enums.AUTOAIM_ENEMY)
            isMultiDamage = true;
        super.calculateCardDamage(mo);
    }

    @Override
    public void initializeDescription() {
        boolean inReward = false;
        if(adRoom() != null) {
            for (RewardItem item : adRoom().rewards) {
                if (item.type == RewardItem.RewardType.CARD && item.cards.contains(this)) {
                    inReward = true;
                    break;
                }
            }
        }

        if (resonance != null && adRoom() != null && adp() != null && !inReward
                && ((!AbstractDungeon.gridSelectScreen.forUpgrade) || !AbstractDungeon.gridSelectScreen.confirmScreenUp)) {
            baseDamage = resonance.getDamage();
            type = resonance.getCardType();
            target = resonance.getCardTarget();
            rawDescription = resonance.getDescription();
            overrideRawDesc = true;
        } else
            overrideRawDesc = false;
        super.initializeDescription();
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractResonantCard copy = (AbstractResonantCard) super.makeStatEquivalentCopy();
        copy.resonance = resonance.resClone();
        copy.initializeDescription();
        initializeDescription();
        return copy;
    }

    public void upp() {
    }
}