package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.cardDraw;
import static theExile.util.Wiz.shuffleIn;

public class ChaoticMind extends AbstractExileCard {
    public final static String ID = makeID(ChaoticMind.class.getSimpleName());
    private final static int COST = 0;
    private final static int MAGIC = 1;
    private final static int SECOND_MAGIC = 2;
    private final static int UPGRADE_SECOND = 1;

    public ChaoticMind() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
        exhaust = true;
        cardsToPreview = new Cycler();
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        cardDraw(magicNumber);
        shuffleIn(new Cycler(), secondMagic);
    }

    public void upp() {
        upMagic2(UPGRADE_SECOND);
    }
}