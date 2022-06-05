package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.cards.AbstractArcanistCard.elenum.DARK;

public class VoidBall extends AbstractArcanistCard {
    public final static String ID = makeID(VoidBall.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int COST = 1;

    public VoidBall() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(DARK);
        isMultiDamage = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        allDmg();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}