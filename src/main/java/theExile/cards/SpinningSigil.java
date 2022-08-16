package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class SpinningSigil extends AbstractExileCard {
    public final static String ID = makeID(SpinningSigil.class.getSimpleName());
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int COST = -2;

    public SpinningSigil() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, ExileMod.Enums.AUTOAIM_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        sigil = true;
        addModifier(elenum.FIRE);
        isMultiDamage = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster strongestMonster = getHighestHealthEnemy();
        calculateCardDamage(strongestMonster);
        onTarget(strongestMonster);
    }

    @Override
    public void onTarget(AbstractMonster m) {
        dmg(m);
        cardDraw(1);
        discard(1);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}