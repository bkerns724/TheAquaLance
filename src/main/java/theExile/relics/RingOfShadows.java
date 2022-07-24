package theExile.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theExile.TheExile;
import theExile.cards.Hallucination;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class RingOfShadows extends AbstractExileRelic {
    public static final String ID = makeID(RingOfShadows.class.getSimpleName());
    private static final int BLOCK_AMOUNT = 7;

    public RingOfShadows() {
        super(ID, RelicTier.SHOP, LandingSound.CLINK, TheExile.Enums.EXILE_BLARPLE_COLOR);
        cardToPreview = new Hallucination();
        amount = BLOCK_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard.color == AbstractCard.CardColor.CURSE)
            atb(new GainBlockAction(adp(), BLOCK_AMOUNT));
    }

    @Override
    public void onEquip() {
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Hallucination(), Settings.WIDTH*0.5f,
                Settings.HEIGHT*0.5f));
        counter = 0;

        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.type == AbstractCard.CardType.CURSE)
                ++counter;
        }
    }

    public void onMasterDeckChange() {
        counter = 0;

        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.type == AbstractCard.CardType.CURSE)
                ++counter;
        }
    }
}
