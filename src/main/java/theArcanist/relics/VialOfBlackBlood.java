package theArcanist.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theArcanist.TheArcanist;
import theArcanist.cards.DemonsCantrip;

import java.util.Iterator;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;

public class VialOfBlackBlood extends AbstractArcanistRelic {
    public static final String ID = makeID(VialOfBlackBlood.class.getSimpleName());
    private boolean calledTransform = true;
    private int count = 0;

    public VialOfBlackBlood() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        cardToPreview = new DemonsCantrip();
        setUpdatedDescription();
    }

    @Override
    public void onEquip() {
        calledTransform = false;
        Iterator<AbstractCard> i = AbstractDungeon.player.masterDeck.group.iterator();

        while(true) {
            AbstractCard e;
            do {
                if (!i.hasNext()) {
                    if (count > 0) {
                        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

                        for(int j = 0; j < count; ++j) {
                            AbstractCard card = new DemonsCantrip();

                            for (AbstractRelic relic : adp().relics)
                                relic.onPreviewObtainCard(card);

                            group.addToBottom(card);
                        }

                        AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, DESCRIPTIONS[1]);
                    }

                    return;
                }

                e = (AbstractCard)i.next();
            } while(!e.hasTag(AbstractCard.CardTags.STARTER_DEFEND) && !e.hasTag(AbstractCard.CardTags.STARTER_STRIKE));

            i.remove();
            ++count;
        }
    }

    public void update() {
        super.update();
        if (!calledTransform && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.GRID) {
            calledTransform = true;
            AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;
        }
    }
}
