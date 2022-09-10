package theExile.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.FrozenEgg2;
import com.megacrit.cardcrawl.relics.MoltenEgg2;
import com.megacrit.cardcrawl.relics.ToxicEgg2;
import theExile.TheExile;
import theExile.cards.DeafeningChimes;
import theExile.cards.ElementalConflux;
import theExile.cards.SustainedSummon;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class AncientSpellTome extends AbstractExileRelic {
    public static final String ID = makeID(AncientSpellTome.class.getSimpleName());

    public AncientSpellTome() {
        super(ID, RelicTier.SHOP, LandingSound.HEAVY, TheExile.Enums.EXILE_BROWN_COLOR);
        setUpdatedDescription();
    }

    public void onEquip() {
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        tmp.addToTop(new DeafeningChimes());
        tmp.addToTop(new ElementalConflux());
        tmp.addToTop(new SustainedSummon());

        for (int i = 0; i < tmp.size(); i++) {
            AbstractCard card = tmp.group.get(0);
            if (card.type == AbstractCard.CardType.ATTACK && adp().hasRelic(MoltenEgg2.ID))
                card.upgrade();
            else if (card.type == AbstractCard.CardType.SKILL && adp().hasRelic(ToxicEgg2.ID))
                card.upgrade();
            else if (card.type == AbstractCard.CardType.POWER && adp().hasRelic(FrozenEgg2.ID))
                card.upgrade();
        }

        AbstractDungeon.cardRewardScreen.open(tmp.group, null,
                CardCrawlGame.languagePack.getUIString("CardRewardScreen").TEXT[1]);
    }
}
