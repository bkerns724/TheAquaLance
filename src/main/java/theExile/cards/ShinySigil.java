package theExile.cards;

import com.megacrit.cardcrawl.powers.WeakPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.forAllMonstersLiving;

public class ShinySigil extends AbstractExileCard {
    public final static String ID = makeID(ShinySigil.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = -2;

    public ShinySigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    @Override
    public void nonTargetUse() {
        forAllMonstersLiving(m -> applyToEnemy(m, new WeakPower(m, magicNumber, false)));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}