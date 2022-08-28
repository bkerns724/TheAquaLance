package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class CurseArmor extends AbstractExileCard {
    public final static String ID = makeID(CurseArmor.class.getSimpleName());
    private final static int COST = 2;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 4;
    private final static int UPGRADE_SECOND = 1;

    public CurseArmor() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void singleTargetUse(AbstractMonster m) {
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        applyToEnemy(m, new JinxPower(m, secondMagic));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
        upMagic2(UPGRADE_SECOND);
    }
}