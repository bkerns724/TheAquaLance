package theExile.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.MakeAndDiscardAction;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;
import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class HideInSleeve extends AbstractExileCard {
    public final static String ID = makeID(HideInSleeve.class.getSimpleName());
    private final static int UPGRADED_COST = 0;
    private final static int COST = 1;

    public HideInSleeve() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        exhaust = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractExileCard> list = new ArrayList<>();
        AbstractCard c;

        Iterator<AbstractCard> var2 = srcCommonCardPool.group.iterator();
        while(var2.hasNext()) {
            c = var2.next();
            if (c instanceof AbstractExileCard && !c.hasTag(CardTags.HEALING) && ((AbstractExileCard) c).sigil)
                list.add((AbstractExileCard) c);
        }

        var2 = srcUncommonCardPool.group.iterator();
        while(var2.hasNext()) {
            c = var2.next();
            if (c instanceof AbstractExileCard && !c.hasTag(CardTags.HEALING) && ((AbstractExileCard) c).sigil)
                list.add((AbstractExileCard) c);
        }

        var2 = srcRareCardPool.group.iterator();
        while(var2.hasNext()) {
            c = var2.next();
            if (c instanceof AbstractExileCard && !c.hasTag(CardTags.HEALING) && ((AbstractExileCard) c).sigil)
                list.add((AbstractExileCard) c);
        }

        c = list.get(cardRandomRng.random(list.size() - 1));

        atb(new MakeAndDiscardAction(c));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}