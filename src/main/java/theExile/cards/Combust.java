package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class Combust extends AbstractExileCard {
    public final static String ID = makeID(Combust.class.getSimpleName());
    private final static int DAMAGE = 20;
    private final static int UPGRADE_DAMAGE = 5;
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 3;

    public Combust() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
        addModifier(elenum.FIRE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
        applyToSelf(new VigorPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upMagic(UPGRADE_MAGIC);
    }
}