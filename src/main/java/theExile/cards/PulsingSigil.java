package theExile.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;
import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class PulsingSigil extends AbstractExileCard {
    public final static String ID = makeID(PulsingSigil.class.getSimpleName());

    public PulsingSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        sigil = true;
        exhaust = true;
    }

    @Override
    public void onUse(AbstractPlayer p, AbstractMonster m) {
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

        c = list.get(cardRandomRng.random(list.size() - 1));
        c.setCostForTurn(0);

        atb(new MakeTempCardInHandAction(c));
    }

    public void upp() {
        exhaust = false;
    }
}