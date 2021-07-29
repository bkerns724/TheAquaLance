package theAquaLance.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.FindAndExhaustAction;
import theAquaLance.patches.AbstractCardPatch;

import java.util.ArrayList;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class PuritySigil extends AbstractSigilCard {
    public final static String ID = makeID("PuritySigil");
    private final static int MAGIC = 3;

    public PuritySigil() {
        super(ID, CardRarity.UNCOMMON);
        baseMagicNumber = magicNumber = MAGIC;
        AbstractCardPatch.AbstractCardField.sigil.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void onManualDiscard() {
        ArrayList<AbstractCard> cards = new ArrayList<>();
        cards.addAll(adp().drawPile.getCardsOfType(CardType.STATUS).group);
        cards.addAll(adp().discardPile.getCardsOfType(CardType.STATUS).group);
        cards.addAll(adp().hand.getCardsOfType(CardType.STATUS).group);
        if (upgraded) {
            cards.addAll(adp().drawPile.getCardsOfType(CardType.CURSE).group);
            cards.addAll(adp().discardPile.getCardsOfType(CardType.CURSE).group);
            cards.addAll(adp().hand.getCardsOfType(CardType.CURSE).group);
        }
        int size = cards.size();
        if (size <= magicNumber) {
            for (AbstractCard c : cards)
                atb(new FindAndExhaustAction(c));
        }
        else {
            for (int i = 0; i < magicNumber; i++) {
                AbstractCard c = getRandomItem(cards);
                cards.remove(c);
                atb(new FindAndExhaustAction(c));
            }
        }
    }

    public void upp() {
        uDesc();
    }
}