package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.actions.ChaosMagicAction;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class ChaosMagic extends AbstractArcanistCard {
    public final static String ID = makeID("ChaosMagic");
    private final static int COST = 0;

    public ChaosMagic() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ChaosMagicAction());
        if (upgraded && AbstractDungeon.cardRandomRng.random(0, 2) == 0)
            atb(new ChaosMagicAction());
    }

    public void upp() {
    }
}