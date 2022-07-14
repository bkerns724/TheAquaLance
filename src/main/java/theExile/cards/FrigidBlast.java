package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.FrigidBlastAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class
FrigidBlast extends AbstractExileCard {
    public final static String ID = makeID(FrigidBlast.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int COST = -1;

    public FrigidBlast() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.ICE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new FrigidBlastAction(m, damage, damageTypeForTurn, freeToPlayOnce, energyOnUse, getAttackEffect()));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}