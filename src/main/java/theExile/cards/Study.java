package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.ExileStudyPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Study extends AbstractExileCard {
    public final static String ID = makeID(Study.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int SECOND_MAGIC = 3;
    private final static int UPGRADE_SECOND = 1;
    private final static int COST = 1;

    public Study() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        secondMagic = baseSecondMagic = SECOND_MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ExileStudyPower(magicNumber, secondMagic));
    }

    public void upp() {
        upMagic2(UPGRADE_SECOND);
    }
}