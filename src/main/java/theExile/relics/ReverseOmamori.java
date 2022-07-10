package theExile.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Clumsy;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theExile.TheExile;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class ReverseOmamori extends AbstractExileRelic {
    public static final String ID = makeID(ReverseOmamori.class.getSimpleName());
    public static final int CURSE_AMOUNT = 2;

    public ReverseOmamori() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = CURSE_AMOUNT;
        cardToPreview = new Clumsy();
        setUpdatedDescription();
    }

    @Override
    public void atBattleStart() {
        if (counter > 0) {
            flash();
            atb(new RelicAboveCreatureAction(adp(), this));
            applyToSelf(new DexterityPower(adp(), counter));
        }
    }

    @Override
    public void onEquip() {
        for (int i = 0; i < CURSE_AMOUNT; i++) {
            AbstractCard card = new Clumsy();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(card, (float) Settings.WIDTH / 2.0F,
                    (float) Settings.HEIGHT / 2.0F));
        }
    }

    @Override
    public void onMasterDeckChange() {
        countCurse();
    }

    private void countCurse() {
        counter = 0;
        for (AbstractCard card : adp().masterDeck.group)
            if (card.type == AbstractCard.CardType.CURSE)
                counter++;
    }
}
