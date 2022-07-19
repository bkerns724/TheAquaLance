package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.TempNegStrengthPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class ForceBolt extends AbstractExileCard {
    public final static String ID = makeID(ForceBolt.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public ForceBolt() { super(ID, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY); }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.FORCE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
        applyToEnemy(m, new TempNegStrengthPower(m, magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upMagic(UPGRADE_MAGIC);
    }
}