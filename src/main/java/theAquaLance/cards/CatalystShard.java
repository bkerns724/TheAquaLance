package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.EmbedAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class CatalystShard extends AbstractEmbedCard {
    public final static String ID = makeID("CatalystShard");
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public CatalystShard() {
        super(ID, 2, CardRarity.UNCOMMON);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }

    public int getCatalystAmount() {
        return magicNumber;
    }
}