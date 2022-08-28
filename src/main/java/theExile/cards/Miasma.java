package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theExile.ExileMod;
import theExile.powers.TempNegStrengthPower;
import theExile.vfx.MiasmaEffect;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.vfx;

public class Miasma extends AbstractExileCard {
    public final static String ID = makeID(Miasma.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 2;
    private final static int SECOND_MAGIC = 1;
    private final static int COST = 1;

    public Miasma() {
        super(ID, COST, CardType.SKILL, ExileMod.Enums.UNIQUE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void singleTargetUse(AbstractMonster m) {
        vfx(new MiasmaEffect(m.hb.cX, m.hb.cY));
        applyToEnemy(m, new WeakPower(m, secondMagic, false));
        applyToEnemy(m, new TempNegStrengthPower(m, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}