package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.EmbedAction;
import theAquaLance.actions.TriggerEnemyMarkAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class InscribedShard extends AbstractEmbedCard {
    public final static String ID = makeID("InscribedShard");
    private final static int DAMAGE = 3;
    private final static int UPG_DAMAGE = 1;
    private final static int MAGIC = 3;
    private final static int UPG_MAGIC = 2;

    public InscribedShard() {
        super(ID, 1, CardRarity.UNCOMMON);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    @Override
    public int getMarkAmount() {
        return magicNumber;
    }

    public int getTriggerAmount() {return 1;}

    public void onFinisher(AbstractCreature host) {
        atb(new TriggerEnemyMarkAction((AbstractMonster) host));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        upgradeDamage(UPG_DAMAGE);
    }
}