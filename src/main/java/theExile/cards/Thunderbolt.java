package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.getHighestHealthEnemy;

public class Thunderbolt extends AbstractExileCard {
    public final static String ID = makeID(Thunderbolt.class.getSimpleName());
    private final static int DAMAGE = 12;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 1;

    public Thunderbolt() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        isMultiDamage = true;
        addModifier(elenum.LIGHTNING);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster mon = getHighestHealthEnemy();
        calculateCardDamage(mon);
        dmg(mon);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}