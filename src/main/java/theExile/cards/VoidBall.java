package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.DARK;

public class VoidBall extends AbstractExileCard {
    public final static String ID = makeID(VoidBall.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public VoidBall() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
        isMultiDamage = true;
        addModifier(DARK);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        allDmg();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}