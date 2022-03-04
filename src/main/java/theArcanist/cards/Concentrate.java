package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.DrawNextTurnPower;
import theArcanist.powers.EnergizedArcanistPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Concentrate extends AbstractArcanistCard {
    public final static String ID = makeID("Concentrate");
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;
    private final static int ENERGY_AMOUNT = 1;

    public Concentrate() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DrawNextTurnPower(magicNumber));
        applyToSelf(new EnergizedArcanistPower(ENERGY_AMOUNT));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}