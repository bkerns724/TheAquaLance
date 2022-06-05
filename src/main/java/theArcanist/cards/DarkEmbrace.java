package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.DarkerEmbracePower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.applyToEnemy;

public class DarkEmbrace extends AbstractArcanistCard {
    public final static String ID = makeID(DarkEmbrace.class.getSimpleName());
    private final static int DAMAGE = 3;
    private final static int MAGIC = 1;
    private final static int COST = 0;

    public DarkEmbrace() {
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
        applyToEnemy(m, new DarkerEmbracePower(m, magicNumber));
    }

    public void upp() {
        exhaust = false;
    }
}