package theArcanist.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.actions.IgnorePainAction;
import theArcanist.powers.IgnorePainPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class IgnorePain extends AbstractArcanistCard {
    public final static String ID = makeID(IgnorePain.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public IgnorePain() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new IgnorePainPower(p, magicNumber));
        if (upgraded) {
            for (AbstractCard card : adp().drawPile.group) {
                if (card.type == AbstractCard.CardType.STATUS) {
                    atb(new IgnorePainAction(card));
                    return;
                }
            }
            for (AbstractCard card : adp().discardPile.group) {
                if (card.type == AbstractCard.CardType.STATUS) {
                    atb(new IgnorePainAction(card));
                    return;
                }
            }
        }
    }

    public void upp() {
    }
}