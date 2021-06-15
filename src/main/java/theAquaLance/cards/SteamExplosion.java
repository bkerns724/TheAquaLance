package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theAquaLance.actions.PopAction;
import theAquaLance.powers.HobbledPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class SteamExplosion extends AbstractEasyCard {
    public final static String ID = makeID("SteamExplosion");
    private final static int SECOND_DAMAGE = 10;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int POP_AMOUNT = 1;

    public SteamExplosion() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseSecondDamage = SECOND_DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmgTwo(m, AbstractGameAction.AttackEffect.FIRE);
        int count = getShardCount(m);
        if (count >= POP_AMOUNT) {
            onPopAction(m);
            atb(new PopAction(m));
        }
    }

    @Override
    protected void popBonus(AbstractCreature target) {
        forAllMonstersLiving(mon -> {
            applyToEnemy(mon, new WeakPower(mon, magicNumber, false));
            applyToEnemy(mon, new HobbledPower(mon, magicNumber));
            applyToEnemy(mon, new VulnerablePower(mon, magicNumber, false));
        });
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}