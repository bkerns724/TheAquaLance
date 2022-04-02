package theArcanist.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theArcanist.TheArcanist;
import theArcanist.cards.Wrath;

import static theArcanist.ArcanistMod.makeID;

public class HexedStaff extends AbstractArcanistRelic {
    public static final String ID = makeID(HexedStaff.class.getSimpleName());

    public HexedStaff() {
        super(ID, RelicTier.SPECIAL, LandingSound.HEAVY, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        cardToPreview = new Wrath();
        amount = 1;
        setUpdatedDescription();
    }

    @Override
    public void onEquip() {
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Wrath(), (float) Settings.WIDTH / 2.0F,
                (float) Settings.HEIGHT / 2.0F));

        counter = 0;

        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.type == AbstractCard.CardType.CURSE)
                ++counter;
        }

        grayscale = counter == 0;
    }

    public void onMasterDeckChange() {
        counter = 0;

        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.type == AbstractCard.CardType.CURSE)
                ++counter;
        }

        grayscale = counter == 0;
    }
}
