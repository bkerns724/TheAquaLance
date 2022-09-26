package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.getHighestHealthEnemy;

public class TranslucentSigil extends AbstractExileCard {
    public final static String ID = makeID(TranslucentSigil.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 2;

    public TranslucentSigil() {
        super(ID, -2, CardType.ATTACK, CardRarity.UNCOMMON, ExileMod.Enums.AUTOAIM_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
        addModifier(elenum.FORCE);
    }

    @Override
    public AbstractMonster getTarget() {
        return getHighestHealthEnemy();
    }

    @Override
    public void autoTargetUse(AbstractMonster m) {
        calculateCardDamage(m);
        for (int i = 0; i < magicNumber; i++)
            dmg(m);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}