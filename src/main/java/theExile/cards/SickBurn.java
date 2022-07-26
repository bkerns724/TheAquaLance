package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class SickBurn extends AbstractExileCard {
    public final static String ID = makeID(SickBurn.class.getSimpleName());
    private final static int DAMAGE = 10;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 8;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 2;

    public SickBurn() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.FIRE);
        exhaust = true;
        magicOneIsDebuff = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
        applyToEnemy(m, new PoisonPower(m, p, magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}