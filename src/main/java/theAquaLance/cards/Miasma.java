package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theAquaLance.powers.TempNegStrengthPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Miasma extends AbstractEasyCard {
    public final static String ID = makeID("Miasma");
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public Miasma() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
        applyToEnemy(m, new TempNegStrengthPower(m, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}