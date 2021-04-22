package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.EmbedAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class PoisonShard extends AbstractEmbedCard {
    public final static String ID = makeID("PoisonShard");
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public PoisonShard() {
        super(ID, 1, CardRarity.COMMON);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    @Override
    public void atStartOfTurn(AbstractCreature host) {
        att(new LoseHPAction(host, adp(), magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }

    public String getEmbedDescription() {
        return cardStrings.EXTENDED_DESCRIPTION[0];
    }
}