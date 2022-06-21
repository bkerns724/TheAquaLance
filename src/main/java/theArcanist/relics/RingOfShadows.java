package theArcanist.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theArcanist.TheArcanist;
import theArcanist.cards.Hallucination;
import theArcanist.powers.ShadowcloakPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.applyToSelf;

public class RingOfShadows extends AbstractArcanistRelic {
    public static final String ID = makeID(RingOfShadows.class.getSimpleName());
    private static final int SHADOW_AMOUNT = 1;

    public RingOfShadows() {
        super(ID, RelicTier.SHOP, LandingSound.CLINK, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        cardToPreview = new Hallucination();
        amount = SHADOW_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard.color == AbstractCard.CardColor.CURSE)
            applyToSelf(new ShadowcloakPower(adp(), amount));
    }

    @Override
    public void onEquip() {
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Hallucination(), Settings.WIDTH*0.5f,
                Settings.HEIGHT*0.5f));
    }
}
