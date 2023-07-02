package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theExile.powers.CorrodedPower;
import theExile.util.Wiz;

import static theExile.ExileMod.Enums;
import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class AcidSpray extends AbstractExileCard {
    public final static String ID = makeID(AcidSpray.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int SECOND_MAGIC = 3;
    private final static int UPGRADE_SECOND = 1;
    private final static int COST = 1;

    public AcidSpray() {
        super(ID, COST, CardType.SKILL, Enums.UNIQUE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void singleTargetUse(AbstractMonster m) {
        Wiz.vfx(new FlashAtkImgEffect(m.hb.cX, m.hb.cY, Enums.ACID_M));
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        applyToEnemy(m, new CorrodedPower(m, secondMagic));
    }

    public void upp() {
        upMagic2(UPGRADE_SECOND);
    }
}