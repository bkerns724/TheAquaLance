package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.EmbedAction;
import theAquaLance.patches.AbstractCardPatch;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class PureShard extends AbstractEmbedCard {
    public final static String ID = makeID("PureShard");
    private final static int DAMAGE = 1;
    private final static int MAGIC = 1;

    public PureShard() {
        super(ID, 3, CardRarity.RARE);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    public void upp() {
        AbstractCardPatch.AbstractCardField.replenish.set(this, true);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    }

    public String getEmbedDescription() {
        return cardStrings.EXTENDED_DESCRIPTION[0];
    }
}