package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theAquaLance.actions.PopAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class SteamBlast extends AbstractEasyCard {
    public final static String ID = makeID("SteamBlast");
    private final static int DAMAGE = 15;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int POP_AMOUNT = 2;

    public SteamBlast() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int shardCount = getShardCount(m);
        if (shardCount >= POP_AMOUNT) {
            allDmg(AbstractGameAction.AttackEffect.FIRE);
            forAllMonstersLiving((mo) -> {
                applyToEnemy(mo, new WeakPower(mo, magicNumber, false));
                applyToEnemy(mo, new VulnerablePower(mo, magicNumber, false));
            });
            atb(new PopAction(m, POP_AMOUNT));
        }
        else {
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            applyToEnemy(m, new WeakPower(m, magicNumber, false));
            applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}