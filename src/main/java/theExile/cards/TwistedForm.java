package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.TwistedFormPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class TwistedForm extends AbstractExileCard {
    public final static String ID = makeID(TwistedForm.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int SECOND_MAGIC = 12;
    private final static int UPGRADE_SECOND = 6;
    private final static int COST = 3;

    public AbstractMonster targetMonsterForTwisted;

    public TwistedForm() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        if (m != null)
            targetMonsterForTwisted = m;
        applyToEnemy(m, new TwistedFormPower(m, magicNumber, secondMagic));
    }

    public void upp() {
        upMagic2(UPGRADE_SECOND);
    }
}