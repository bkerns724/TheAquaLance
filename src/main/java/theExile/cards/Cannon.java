package theExile.cards;

import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.shuffleIn;

public class Cannon extends AbstractExileCard {
    public final static String ID = makeID(Cannon.class.getSimpleName());
    private final static int DAMAGE = 16;
    private final static int UPGRADE_DAMAGE = 5;
    private final static int MAGIC = 2;
    private final static int COST = 1;

    public Cannon() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.LIGHTNING);
        cardsToPreview = new Dazed();
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
        shuffleIn(new Dazed(), magicNumber);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}