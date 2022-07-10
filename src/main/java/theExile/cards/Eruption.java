package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;

public class Eruption extends AbstractExileCard {
    public final static String ID = makeID(Eruption.class.getSimpleName());
    private final static int DAMAGE = 26;
    private final static int UPGRADE_DAMAGE = 7;
    private final static int COST = 3;

    public Eruption() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        isMultiDamage = true;
        addModifier(elenum.FIRE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        allDmg();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}