package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.PopAction;
import theAquaLance.powers.IceSpikesPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class IceSlam extends AbstractEasyCard {
    public final static String ID = makeID("IceSlam");
    private final static int DAMAGE = 12;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 8;
    private final static int UPGRADE_MAGIC = 2;
    private final static int POP_AMOUNT = 1;

    public IceSlam() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = getShardCount(m);
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        if (count >= POP_AMOUNT) {
            onPopAction(m);
            atb(new PopAction(m));
        }
    }

    @Override
    protected void onPopAction(AbstractCreature target) {
        applyToEnemy(target, new IceSpikesPower(target, magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}