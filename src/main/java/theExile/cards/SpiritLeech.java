package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.SpiritLeechPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class SpiritLeech extends AbstractExileCard {
    public final static String ID = makeID(SpiritLeech.class.getSimpleName());
    private final static int DAMAGE = 2;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 1;
    private final static int COST = 0;

    public SpiritLeech() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.DARK);
        exhaust = true;
        magicOneIsDebuff = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
        applyToEnemy(m, new SpiritLeechPower(m, magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}