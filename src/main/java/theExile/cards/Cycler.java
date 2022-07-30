package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.cardDraw;

public class Cycler extends AbstractExileCard {
    public final static String ID = makeID(Cycler.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 0;

    public Cycler() {
        super(ID, COST, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        selfRetain = true;
        exhaust = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        cardDraw(magicNumber);
        Wiz.discard(magicNumber);
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}