package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.EmbedAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class UnstableShard extends AbstractEmbedCard {
    public final static String ID = makeID("UnstableShard");
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;

    public UnstableShard() {
        super(ID, 1, CardRarity.UNCOMMON);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    public int getDiscardDamAmount() {return magicNumber;}

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}