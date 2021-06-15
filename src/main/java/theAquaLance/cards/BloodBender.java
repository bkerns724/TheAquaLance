package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.actions.PopAllAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class BloodBender extends AbstractEasyCard {
    public final static String ID = makeID("BloodBender");
    private final static int DAMAGE = 12;
    private final static int UPGRADE_DAMAGE = 4;

    public BloodBender() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        onPopAction(m);
        atb(new PopAllAction(m));
    }

    @Override
    protected void onPopAction(AbstractCreature target) {
        int shardCount = getShardCount(target);
        for (int i = 0; i < shardCount; i++)
            dmg(target, AquaLanceMod.Enums.BLOOD);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}