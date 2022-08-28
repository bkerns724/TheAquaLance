package theExile.cards;

import theExile.powers.DiscardNextTurnPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class LightningBlast extends AbstractExileCard {
    public final static String ID = makeID(LightningBlast.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 2;
    private final static int COST = 0;

    public LightningBlast() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        isMultiDamage = true;
        addModifier(elenum.LIGHTNING);
    }

    public void nonTargetUse() {
        allDmg();
        applyToSelf(new DiscardNextTurnPower(magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}