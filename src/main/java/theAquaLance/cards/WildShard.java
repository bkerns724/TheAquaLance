package theAquaLance.cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theAquaLance.actions.EmbedAction;
import theAquaLance.powers.DrowningPower;
import theAquaLance.powers.HobbledPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class WildShard extends AbstractEmbedCard {
    public final static String ID = makeID("WildShard");
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 1;

    private int magicLeft;

    public WildShard() {
        super(ID, 1, CardRarity.UNCOMMON);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    public void atStartOfPlayerTurn(AbstractCreature host) {
        AbstractMonster m = (AbstractMonster) host;
        int randomDebuff = MathUtils.random(0, 3);
        switch (randomDebuff) {
            case 0:
                applyToEnemy(m, new WeakPower(m, magicNumber, false));
                break;
            case 1:
                applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
                break;
            case 2:
                applyToEnemy(m, new HobbledPower(m, magicNumber));
                break;
            case 3:
                applyToEnemy(m, new DrowningPower(m, magicNumber));
                break;
        }
    }

    public String getEmbedDescription() {
        return cardStrings.EXTENDED_DESCRIPTION[0];
    }
}