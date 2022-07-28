package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.ShockedPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class ShockingSigil extends AbstractExileCard {
    public final static String ID = makeID(ShockingSigil.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = -2;

    public ShockingSigil() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.LIGHTNING);
        isMultiDamage = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        allDmg();
        forAllMonstersLiving(mon -> applyToEnemy(mon, new ShockedPower(mon, getJinxAmount(mon))));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}