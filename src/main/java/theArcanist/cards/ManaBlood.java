package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;
import com.megacrit.cardcrawl.powers.DrawPower;
import theArcanist.ArcanistMod;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class ManaBlood extends AbstractArcanistCard {
    public final static String ID = makeID(ManaBlood.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 2;

    public ManaBlood() {
        super(ID, COST, CardType.POWER, ArcanistMod.Enums.UNIQUE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DrawPower(p, magicNumber));
        applyToSelf(new BerserkPower(p, magicNumber));
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }

    public void upp() {
    }
}