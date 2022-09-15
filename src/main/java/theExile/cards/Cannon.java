package theExile.cards;

import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.getFireEffect;
import static theExile.util.Wiz.shuffleIn;

public class Cannon extends AbstractExileCard {
    public final static String ID = makeID(Cannon.class.getSimpleName());
    private final static int DAMAGE = 14;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public Cannon() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        cardsToPreview = new Burn();
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m, getFireEffect(damage));
        shuffleIn(new Burn(), magicNumber);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}