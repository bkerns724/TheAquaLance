package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.EmbedAction;
import theAquaLance.powers.DrowningPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class WetShard extends AbstractEmbedCard {
    public final static String ID = makeID("WetShard");
    private final static int DAMAGE = 15;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public WetShard() {
        super(ID, 2, CardRarity.RARE);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    public void atStartOfTurn(AbstractCreature host) {
        applyToEnemy((AbstractMonster) host, new DrowningPower(host, magicNumber*getPureShardMult((AbstractMonster) host)));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }

    public String getEmbedDescription() {
        return cardStrings.EXTENDED_DESCRIPTION[0] + magicNumber + cardStrings.EXTENDED_DESCRIPTION[1];
    }
}