package theExile.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.relics.FrozenEgg2;
import com.megacrit.cardcrawl.relics.MoltenEgg2;
import com.megacrit.cardcrawl.relics.ToxicEgg2;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theExile.TheExile;
import theExile.cards.LethalChimes;
import theExile.cards.ElementalConflux;
import theExile.cards.SummonMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class AncientSpellTome extends AbstractExileRelic {
    public static final String ID = makeID(AncientSpellTome.class.getSimpleName());
    private boolean cardsSelected = true;

    public AncientSpellTome() {
        super(ID, RelicTier.SHOP, LandingSound.HEAVY, TheExile.Enums.EXILE_BROWN_COLOR);
        setUpdatedDescription();
    }

    public void onEquip() {
        cardsSelected = false;
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        tmp.addToTop(new LethalChimes());
        tmp.addToTop(new ElementalConflux());
        tmp.addToTop(new SummonMonster());

        for (int i = 0; i < tmp.size(); i++) {
            AbstractCard card = tmp.group.get(0);
            if (card.type == AbstractCard.CardType.ATTACK && adp().hasRelic(MoltenEgg2.ID))
                card.upgrade();
            else if (card.type == AbstractCard.CardType.SKILL && adp().hasRelic(ToxicEgg2.ID))
                card.upgrade();
            else if (card.type == AbstractCard.CardType.POWER && adp().hasRelic(FrozenEgg2.ID))
                card.upgrade();
        }

        if (!AbstractDungeon.isScreenUp)
            AbstractDungeon.gridSelectScreen.open(tmp, 1, DESCRIPTIONS[1] + name + LocalizedStrings.PERIOD, false, false, false, false);
        else {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
            AbstractDungeon.gridSelectScreen.open(tmp, 1, DESCRIPTIONS[1] + name + LocalizedStrings.PERIOD, false, false, false, false);
        }
    }

    public void update() {
        super.update();
        if (!cardsSelected && AbstractDungeon.gridSelectScreen.selectedCards.size() == 1)
            giveCards(AbstractDungeon.gridSelectScreen.selectedCards.get(0));
    }

    public void giveCards(AbstractCard card) {
        cardsSelected = true;
        float displayCount = 0.0F;

        if (card != null)
            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(card,
                    (float) Settings.WIDTH / 3.0F + displayCount, (float) Settings.HEIGHT / 2.0F, false));

        AbstractDungeon.gridSelectScreen.selectedCards.clear();
        AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;
    }
}
