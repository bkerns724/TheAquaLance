package theArcanist.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import theArcanist.TheArcanist;

import java.util.Iterator;

import static theArcanist.ArcanistMod.makeID;

public class RockyEgg extends AbstractArcanistRelic {
    public static final String ID = makeID(RockyEgg.class.getSimpleName());

    public RockyEgg() {
        super(ID, RelicTier.UNCOMMON, LandingSound.HEAVY, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        setUpdatedDescription();
    }

    public void onEquip() {
        Iterator<RewardItem> var1 = AbstractDungeon.combatRewardScreen.rewards.iterator();

        while(true) {
            RewardItem reward;
            do {
                if (!var1.hasNext())
                    return;

                reward = var1.next();
            } while(reward.cards == null);

            for (AbstractCard c : reward.cards)
                onPreviewObtainCard(c);
        }
    }

    public void onPreviewObtainCard(AbstractCard c) {
        onObtainCard(c);
    }

    public void onObtainCard(AbstractCard c) {
        if (c.type == AbstractCard.CardType.ATTACK && c.canUpgrade() && !c.upgraded)
            c.upgrade();
    }

    public boolean canSpawn() {
        return Settings.isEndless || AbstractDungeon.floorNum <= 48;
    }
}
