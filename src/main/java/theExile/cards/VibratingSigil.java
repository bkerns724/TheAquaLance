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

public class VibratingSigil extends AbstractExileCard {
    public final static String ID = makeID(VibratingSigil.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = -2;

    public VibratingSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = getRandomResonantCard();
        if (upgraded)
            c.setCostForTurn(0);
        atb(new MakeTempCardInHandAction(c));
    }

    public static AbstractCard getRandomResonantCard() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        Iterator<AbstractCard> var2 = srcCommonCardPool.group.iterator();

        AbstractCard c;
        while(var2.hasNext()) {
            c = var2.next();
            if (c instanceof AbstractResonantCard && !c.hasTag(CardTags.HEALING)) {
                list.add(c);
            }
        }

        var2 = srcUncommonCardPool.group.iterator();

        while(var2.hasNext()) {
            c = var2.next();
            if (c instanceof AbstractResonantCard && !c.hasTag(CardTags.HEALING)) {
                list.add(c);
            }
        }

        var2 = srcRareCardPool.group.iterator();

        while(var2.hasNext()) {
            c = var2.next();
            if (c instanceof AbstractResonantCard && !c.hasTag(CardTags.HEALING)) {
                list.add(c);
            }
        }

        // I don't like hardcoding but I'm not sure what to do if this is used on a different character
        if (list.size() == 0) {
            list.add(new SonicAgony());
            list.add(new TwinResonance());
            list.add(new Shockwave());
            list.add(new Amplify());
            list.add(new Curse());
        }

        return list.get(cardRandomRng.random(list.size() - 1));
    }

    public void upp() { }
}