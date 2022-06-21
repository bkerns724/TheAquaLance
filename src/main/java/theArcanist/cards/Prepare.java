package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.powers.DiscardNextTurnPower;
import theArcanist.powers.DrawNextTurnPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.applyToSelf;

public class Prepare extends AbstractArcanistCard {
    public final static String ID = makeID(Prepare.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 0;

    // SKILL, UNIQUE, SELF
    public Prepare() {
        super(ID, COST, CardType.SKILL, ArcanistMod.Enums.UNIQUE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DrawNextTurnPower(magicNumber));
        applyToSelf(new DiscardNextTurnPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}