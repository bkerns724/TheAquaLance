package theExile.cards;

import theExile.powers.NoceboPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.forAllMonstersLiving;

public class TrickStaff extends AbstractExileCard {
    public final static String ID = makeID(TrickStaff.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 1;

    public TrickStaff() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        forAllMonstersLiving(mon -> {
            calculateCardDamage(mon);
            if (damageModList.isEmpty())
                dmg(mon, Wiz.getBluntEffect(damage));
            else
                dmg(mon);
            applyToEnemy(mon, new NoceboPower(mon, 1));
        });
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upMagic(UPGRADE_MAGIC);
    }
}