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

public class BlackCandle extends AbstractArcanistRelic {
    public static final String ID = makeID(BlackCandle.class.getSimpleName());
    private static final int shadowAmount = 1;

    public BlackCandle() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        cardToPreview = new Hallucination();
        amount = shadowAmount;
        setUpdatedDescription();
    }

    @Override
    public void onEquip() {
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Hallucination(), (float) Settings.WIDTH / 2.0F,
                (float) Settings.HEIGHT / 2.0F));
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard.color == AbstractCard.CardColor.CURSE)
            applyToSelf(new ShadowcloakPower(adp(), amount));
    }
}
