package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.CorrodedPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.getAcidEffect;

public class AcidGlob extends AbstractExileCard {
    public final static String ID = makeID(AcidGlob.class.getSimpleName());
    private final static int DAMAGE = 3;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 0;

    public AcidGlob() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m, getAcidEffect(damage));
        Wiz.applyToEnemy(m, new CorrodedPower(m, magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}