package theArcanist.cards;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

// Storing json thing because it doesn't take comments
// "EXTENDED_DESCRIPTION": ["I'm not going to get hoisted by my own petard'."]

public class AdrenalineSigil extends AbstractSigilCard {
    public final static String ID = makeID("AdrenalineSigil");
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public AdrenalineSigil() {
        super(ID, CardRarity.RARE, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void onManualDiscard() {
        AbstractPlayer p = AbstractDungeon.player;
        CardGroup cGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        cGroup.group.addAll(p.hand.getCardsOfType(CardType.STATUS).group);
        cGroup.group.addAll(p.drawPile.getCardsOfType(CardType.STATUS).group);
        cGroup.group.addAll(p.discardPile.getCardsOfType(CardType.STATUS).group);

        Random random = AbstractDungeon.miscRng;
        for (int i = 0; i < magicNumber; i++) {
            if (cGroup.group.size() > 0) {
                int x = random.random(0, cGroup.group.size());
                // If I was worried about performance I could make this better but it isn't going to be an issue
                atb(new ExhaustSpecificCardAction(cGroup.group.get(x), p.hand, true));
                atb(new ExhaustSpecificCardAction(cGroup.group.get(x), p.drawPile, true));
                atb(new ExhaustSpecificCardAction(cGroup.group.get(x), p.discardPile, true));
                cGroup.group.remove(x);
            }
        }
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}