package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.brrZerkAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class BrrZerk extends AbstractExileCard {
    public final static String ID = makeID(BrrZerk.class.getSimpleName());
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = -1;

    public BrrZerk() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.ICE);
        exhaust = true;
    }

    public void singleTargetUse(AbstractMonster m) {
        DamageModContainer container = new DamageModContainer(this, DamageModifierManager.modifiers(this));
        atb(new brrZerkAction(m, this, freeToPlayOnce, energyOnUse, container));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}