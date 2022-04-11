package theArcanist.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.actions.ChaosMagicAction;
import theArcanist.relics.DetailedContract;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class ChaosMagic extends AbstractArcanistCard {
    public final static String ID = makeID(ChaosMagic.class.getSimpleName());
    private final static int COST = 1;
    private final static int MAGIC = 2;


    public ChaosMagic() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        magicNumber = baseMagicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new ChaosMagicAction());
        if (adp().hasRelic(DetailedContract.ID))
            atb(new ChaosMagicAction());
        if (upgraded && AbstractDungeon.cardRandomRng.random(0, 2) == 0)
            atb(new ChaosMagicAction());
        atb(new DrawCardAction(magicNumber));
        discard(1);
    }

    public void upp() {
    }
}