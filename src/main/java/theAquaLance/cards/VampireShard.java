package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.EmbedAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class VampireShard extends AbstractEmbedCard {
    public final static String ID = makeID("VampireShard");
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public VampireShard() {
        super(ID, 1, CardRarity.BASIC);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeDamage(UPGRADE_DAMAGE);
    }

    public String getEmbedDescription() {
        return cardStrings.EXTENDED_DESCRIPTION[0] + magicNumber + cardStrings.EXTENDED_DESCRIPTION[1];
    }

    public void onDeath() {
        att(new HealAction(adp(), adp(), magicNumber));
    }
}