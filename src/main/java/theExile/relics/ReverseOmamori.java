package theExile.relics;

import basemod.helpers.CardPowerTip;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Clumsy;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theExile.TheExile;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class ReverseOmamori extends AbstractExileRelic {
    public static final String ID = makeID(ReverseOmamori.class.getSimpleName());
    public static final int CURSE_AMOUNT = 2;
    private static final AbstractCard CARD_TO_PREVIEW = new Clumsy();

    public ReverseOmamori() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheExile.Enums.EXILE_BROWN_COLOR);
        amount = CURSE_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void atBattleStart() {
        if (counter > 0) {
            flash();
            applyToSelfTop(new DexterityPower(adp(), counter));
            att(new RelicAboveCreatureAction(adp(), this));
        }
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
    public void onEquip() {
        AbstractCard card = new Clumsy();
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(card.makeStatEquivalentCopy(), (float) Settings.WIDTH * 0.6f,
                (float) Settings.HEIGHT / 2.0F));
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(card.makeStatEquivalentCopy(), (float) Settings.WIDTH * 0.4f,
                (float) Settings.HEIGHT / 2.0F));
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
