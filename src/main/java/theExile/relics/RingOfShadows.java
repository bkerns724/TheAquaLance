package theExile.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theExile.TheExile;
import theExile.cards.Hallucination;
import theExile.powers.ShadowcloakPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class RingOfShadows extends AbstractExileRelic {
    public static final String ID = makeID(RingOfShadows.class.getSimpleName());
    private static final int SHADOW_AMOUNT = 1;

    public RingOfShadows() {
        super(ID, RelicTier.SHOP, LandingSound.CLINK, TheExile.Enums.EXILE_BLARPLE_COLOR);
        cardToPreview = new Hallucination();
        amount = SHADOW_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard.color == AbstractCard.CardColor.CURSE) {
            flash();
            atb(new RelicAboveCreatureAction(adp(), this));
            applyToSelf(new ShadowcloakPower(adp(), amount));
        }
    }

    @Override
    public void onEquip() {
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Hallucination(), Settings.WIDTH*0.5f,
                Settings.HEIGHT*0.5f));
    }
}
