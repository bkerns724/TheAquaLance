package theExile.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theExile.TheExile;
import theExile.cards.AbstractResonantCard;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;
import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class ShinyKazoo extends AbstractExileRelic {
    public static final String ID = makeID(ShinyKazoo.class.getSimpleName());

    public ShinyKazoo() {
        super(ID, RelicTier.UNCOMMON, LandingSound.MAGICAL, TheExile.Enums.EXILE_BROWN_COLOR);
        setUpdatedDescription();
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard instanceof AbstractResonantCard) {
            ArrayList<AbstractCard> list = new ArrayList<>();
            AbstractCard c;

            Iterator<AbstractCard> var2 = srcCommonCardPool.group.iterator();
            while(var2.hasNext()) {
                c = var2.next();
                if (c instanceof AbstractResonantCard && !c.hasTag(AbstractCard.CardTags.HEALING))
                    list.add(c);
            }

            var2 = srcUncommonCardPool.group.iterator();
            while(var2.hasNext()) {
                c = var2.next();
                if (c instanceof AbstractResonantCard && !c.hasTag(AbstractCard.CardTags.HEALING))
                    list.add(c);
            }

            var2 = srcRareCardPool.group.iterator();
            while(var2.hasNext()) {
                c = var2.next();
                if (c instanceof AbstractResonantCard && !c.hasTag(AbstractCard.CardTags.HEALING))
                    list.add(c);
            }

            c = list.get(cardRandomRng.random(list.size() - 1));
            AbstractCard output = c.makeStatEquivalentCopy();
            output.setCostForTurn(0);

            atb(new MakeTempCardInHandAction(output));
        }
    }
}
