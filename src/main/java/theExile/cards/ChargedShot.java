package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.ChargedShotAction;

import static theExile.ExileMod.makeID;

public class ChargedShot extends AbstractExileCard {
    public final static String ID = makeID(ChargedShot.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int COST = -1;

    public ChargedShot() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.FORCE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        new ChargedShotAction(m, damage, damageTypeForTurn, freeToPlayOnce, energyOnUse, getAttackEffect());
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}