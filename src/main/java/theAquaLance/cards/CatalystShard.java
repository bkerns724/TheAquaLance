package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.actions.EmbedAction;
import theAquaLance.cards.AbstractEasyCard;
import theAquaLance.powers.EmbedPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class CatalystShard extends AbstractEmbedCard {
    public final static String ID = makeID("CatalystShard");
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;

    public CatalystShard() {
        super(ID, 1, CardRarity.UNCOMMON);
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

    public String getEmbedDescription() {
        return cardStrings.EXTENDED_DESCRIPTION[0] + magicNumber + cardStrings.EXTENDED_DESCRIPTION[1];
    }

    @Override
    public void onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature host) {
        if (power.type == AbstractPower.PowerType.DEBUFF && !(power instanceof EmbedPower) && host == target)
            att(new LoseHPAction(target, adp(), magicNumber*getPureShardMult((AbstractMonster)target)));
    }
}