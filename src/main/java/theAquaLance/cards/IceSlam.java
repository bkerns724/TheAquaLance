package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.PopAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class IceSlam extends AbstractEasyCard {
    public final static String ID = makeID("IceSlam");
    private final static int DAMAGE = 13;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int BLOCK = 13;
    private final static int UPGRADE_BLOCK = 4;
    private final static int MAGIC = 1;

    public IceSlam() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int shardCount = getShardCount(m);
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        if (shardCount >= magicNumber) {
            atb(new PopAction(m, magicNumber));
            blck();
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeBlock(UPGRADE_BLOCK);
    }
}