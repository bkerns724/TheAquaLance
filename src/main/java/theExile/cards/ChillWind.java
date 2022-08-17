package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.cardDraw;

public class ChillWind extends AbstractExileCard {
    public final static String ID = makeID(ChillWind.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 2;
    private final static int COST = 2;

    public ChillWind() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
        cardDraw(secondMagic);
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}