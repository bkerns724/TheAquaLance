package theAquaLance.cards;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.EmbedAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class JaggedShard extends AbstractEmbedCard {
    public final static String ID = makeID("JaggedShard");
    private final static int DAMAGE = 4;
    private final static int UPGRADE_DAMAGE = 2;

    public JaggedShard() {
        super(ID, 0, CardRarity.COMMON);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }

    public String getEmbedDescription() {
        return cardStrings.EXTENDED_DESCRIPTION[0];
    }
}