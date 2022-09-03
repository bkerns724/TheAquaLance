package theExile.relics;

import basemod.helpers.CardPowerTip;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theExile.TheExile;
import theExile.cards.Whispers;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class RingOfWhispers extends AbstractExileRelic {
    public static final String ID = makeID(RingOfWhispers.class.getSimpleName());
    private static final int BLOCK_AMOUNT = 8;
    private static final int THRESHOLD_FOR_SPAWN = 2;
    private static final AbstractCard CARD_TO_PREVIEW = new Whispers();

    public RingOfWhispers() {
        super(ID, RelicTier.SHOP, LandingSound.CLINK, TheExile.Enums.EXILE_BROWN_COLOR);
        amount = BLOCK_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void setUpdatedDescription() {
        description = getUpdatedDescription();
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new CardPowerTip(CARD_TO_PREVIEW));
        initializeTips();
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard.color == AbstractCard.CardColor.CURSE)
            atb(new GainBlockAction(adp(), BLOCK_AMOUNT));
    }

    @Override
    public void onEquip() {
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Whispers(), Settings.WIDTH*0.5f,
                Settings.HEIGHT*0.5f));
    }

    @Override
    public boolean canSpawn() {
        int count = 0;

        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.type == AbstractCard.CardType.CURSE)
                ++count;
        }

        return count >= THRESHOLD_FOR_SPAWN;
    }
}
