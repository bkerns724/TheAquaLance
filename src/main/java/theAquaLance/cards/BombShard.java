package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.actions.EmbedAction;
import theAquaLance.powers.EmbedPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class BombShard extends AbstractEmbedCard {
    public final static String ID = makeID("BombShard");
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int SECOND_DAMAGE = 3;
    private final static int MAGIC_NUMBER = 6;
    private final static int UPGRADE_MAGIC = 2;
    private final static int SECOND_MAGIC = 3;
    private int counter = 0;

    public BombShard() {
        super(ID, 1, CardRarity.UNCOMMON);
        baseDamage = DAMAGE;
        baseSecondDamage = SECOND_DAMAGE;
        magicNumber = baseMagicNumber = MAGIC_NUMBER;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
        counter = secondMagic;
    }

    @Override
    public void atStartOfTurn(AbstractCreature host) {
        counter--;
        EmbedPower pow = (EmbedPower) host.getPower(EmbedPower.POWER_ID);
        if (counter <= 0) {
            for (int i = 0; i < magicNumber; i++)
                dmgTwo(host, AquaLanceMod.Enums.WATER);
            if (pow != null)
                pow.popCard(this);
            counter = secondMagic;
        }
        else
            pow.updateDescription();

    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }

    public String getDesc() {
        String[] DESC = cardStrings.EXTENDED_DESCRIPTION;
        if (counter == 1)
            return DESC[0] + counter + DESC[1] + secondDamage + DESC[3] + magicNumber + DESC[4];
        else
            return DESC[0] + counter + DESC[2] + secondDamage + DESC[3] + magicNumber + DESC[4];
    }
}