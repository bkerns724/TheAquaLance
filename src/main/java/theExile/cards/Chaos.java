package theExile.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;
import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class Chaos extends AbstractExileCard {
    public final static String ID = makeID(Chaos.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 2;

    public Chaos() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void nonTargetUse() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        AbstractCard c;

        Iterator<AbstractCard> var2 = srcCommonCardPool.group.iterator();
        while(var2.hasNext()) {
            c = var2.next();
            if (c instanceof AbstractResonantCard && !c.hasTag(CardTags.HEALING))
                list.add(c);
        }

        var2 = srcUncommonCardPool.group.iterator();
        while(var2.hasNext()) {
            c = var2.next();
            if (c instanceof AbstractResonantCard && !c.hasTag(CardTags.HEALING))
                list.add(c);
        }

        var2 = srcRareCardPool.group.iterator();
        while(var2.hasNext()) {
            c = var2.next();
            if (c instanceof AbstractResonantCard && !c.hasTag(CardTags.HEALING))
                list.add(c);
        }

        for (int i = 0; i < magicNumber; i++) {
            c = list.get(cardRandomRng.random(list.size() - 1)).makeStatEquivalentCopy();
            c.setCostForTurn(0);
            atb(new MakeTempCardInHandAction(c));
        }
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}