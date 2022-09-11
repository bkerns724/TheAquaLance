package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EquilibriumPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class TelekineticBlades extends AbstractExileCard {
    public final static String ID = makeID(TelekineticBlades.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 2;

    public TelekineticBlades() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            if (damageModList.isEmpty())
                dmg(m, Wiz.getSlashEffect(damage));
            else
                dmg(m);
        }
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new EquilibriumPower(adp(), 1));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}