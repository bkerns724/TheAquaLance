package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theArcanist.powers.TempNegStrengthPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Miasma extends AbstractArcanistCard {
    public final static String ID = makeID("Miasma");
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 2;
    private final static int SECOND_MAGIC = 1;
    private final static int COST = 1;

    public Miasma() {
        super(ID, COST, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new WeakPower(m, secondMagic, false));
        applyToEnemy(m, new TempNegStrengthPower(m, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}