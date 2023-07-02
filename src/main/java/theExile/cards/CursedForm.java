package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.CursedFormPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class CursedForm extends AbstractExileCard {
    public final static String ID = makeID(CursedForm.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 3;

    public CursedForm() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
        isEthereal = true;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        applyToEnemy(m, new CursedFormPower(m, magicNumber));
    }

    public void upp() {
        isEthereal = false;
    }
}