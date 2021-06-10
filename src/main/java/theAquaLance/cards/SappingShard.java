package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.EmbedAction;
import theAquaLance.powers.TempNegStrengthPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class SappingShard extends AbstractEmbedCard {
    public final static String ID = makeID("SappingShard");
    private final static int DAMAGE = 4;
    private final static int MAGIC = 3;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int UPGRADE_MAGIC = 1;

    public SappingShard() {
        super(ID, 2, CardRarity.RARE);
        baseMagicNumber = magicNumber = MAGIC;
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    public int getSapAmount() {return magicNumber;}

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeDamage(UPGRADE_DAMAGE);
    }
}