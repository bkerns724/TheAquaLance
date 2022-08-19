package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.NoceboPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class Nocebo extends AbstractExileCard {
    public final static String ID = makeID(Nocebo.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public Nocebo() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
        for (int i = 0; i < magicNumber; i++)
            applyToEnemy(m, new NoceboPower(m, 1));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}