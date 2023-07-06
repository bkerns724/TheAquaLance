package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.RingingPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class Deafen extends AbstractExileCard {
    public final static String ID = makeID(Deafen.class.getSimpleName());
    private final static int DAMAGE = 20;
    private final static int MAGIC = 8;
    private final static int UPGRADE_MAGIC = 5;
    private final static int RESONANCE = 4;
    private final static int COST = 3;

    public Deafen() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        resonance = RESONANCE;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        if (damageModList.size() == 0)
            dmg(m, Wiz.getSonicEffect(damage));
        else
            dmg(m);
        applyToEnemy(m, new RingingPower(m, magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}