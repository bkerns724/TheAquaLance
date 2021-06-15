package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.actions.PopAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Engulf extends AbstractEasyCard {
    public final static String ID = makeID("Engulf");
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int POP_AMOUNT = 1;

    public Engulf() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AquaLanceMod.Enums.WATER);
        dmg(m, AquaLanceMod.Enums.WATER);
        int count = getShardCount(m);
        if (count >= POP_AMOUNT) {
            onPopAction(m);
            atb(new PopAction(m));
        }
    }

    @Override
    protected void popBonus(AbstractCreature target) {
        dmg(target, AquaLanceMod.Enums.WATER);
        dmg(target, AquaLanceMod.Enums.WATER);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}