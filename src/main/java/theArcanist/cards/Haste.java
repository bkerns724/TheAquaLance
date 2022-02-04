package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.SpeedPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Haste extends AbstractArcanistCard {
    public final static String ID = makeID("Haste");
    private final static int MAGIC = 1;

    public Haste() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SpeedPower(p, magicNumber));
    }

    public void upp() {
        isInnate = true;
    }
}