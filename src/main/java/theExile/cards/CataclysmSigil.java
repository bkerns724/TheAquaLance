package theExile.cards;

import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import theExile.actions.FastLoseHPAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class CataclysmSigil extends AbstractExileCard {
    public final static String ID = makeID(CataclysmSigil.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = -2;

    public CataclysmSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    @Override
    public void nonTargetUse() {
        forAllMonstersLiving(m -> {
            for (AbstractPower pow : m.powers) {
                if (pow.type == AbstractPower.PowerType.DEBUFF && !(pow instanceof GainStrengthPower))
                    atb(new FastLoseHPAction(m, magicNumber));
            }
        });
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}