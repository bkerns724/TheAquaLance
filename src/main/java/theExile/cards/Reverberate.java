package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.RingingPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;
import static theExile.util.Wiz.getSonicEffect;

public class Reverberate extends AbstractExileCard {
    public final static String ID = makeID(Reverberate.class.getSimpleName());
    private final static int DAMAGE = 13;
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;
    private final static int RESONANCE = 2;
    private final static int COST = 2;

    public Reverberate() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        resonance = RESONANCE;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        if (damageModList.isEmpty())
            dmg(m, getSonicEffect(damage));
        else
            dmg(m);
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new RingingPower(magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}