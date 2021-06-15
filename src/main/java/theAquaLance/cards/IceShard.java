package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.EmbedAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class IceShard extends AbstractEmbedCard {
    public final static String ID = makeID("IceShard");
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 3;

    public IceShard() {
        super(ID, 0, CardRarity.COMMON);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}