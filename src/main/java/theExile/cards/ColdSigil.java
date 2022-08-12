package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.getLowestHealthEnemy;

public class ColdSigil extends AbstractExileCard {
    public final static String ID = makeID(ColdSigil.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int COST = -2;

    public ColdSigil() {
        super(ID, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        sigil = true;
        addModifier(elenum.ICE);
        isMultiDamage = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster weakestMonster = getLowestHealthEnemy();
        calculateCardDamage(weakestMonster);
        dmg(weakestMonster);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}