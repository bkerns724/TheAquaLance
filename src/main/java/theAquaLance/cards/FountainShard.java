package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.EmbedAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.atb;

public class FountainShard extends AbstractEmbedCard {
    public final static String ID = makeID("FountainShard");
    private final static int DAMAGE = 3;
    private final static int MAGIC = 5;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int UPGRADE_MAGIC = 2;

    public FountainShard() {
        super(ID, 1, CardRarity.RARE);
        baseMagicNumber = magicNumber = MAGIC;
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    public int getFountainAmount() {return magicNumber;}

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeDamage(UPGRADE_DAMAGE);
    }
}