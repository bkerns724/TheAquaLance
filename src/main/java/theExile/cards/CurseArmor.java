package theExile.cards;

import com.megacrit.cardcrawl.powers.VulnerablePower;
import theExile.powers.JinxPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class CurseArmor extends AbstractExileCard {
    public final static String ID = makeID(CurseArmor.class.getSimpleName());
    private final static int COST = 2;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 3;
    private final static int UPGRADE_SECOND = 1;

    public CurseArmor() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    @Override
    public void nonTargetUse() {
        Wiz.forAllMonstersLiving(mon -> {
            applyToEnemy(mon, new VulnerablePower(mon, magicNumber, false));
            applyToEnemy(mon, new JinxPower(mon, secondMagic));
        });
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
        upMagic2(UPGRADE_SECOND);
    }
}