package theExile.cards;

import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.shuffleIn;

public class Push extends AbstractExileCard {
    public final static String ID = makeID(Push.class.getSimpleName());
    private final static int BLOCK = 14;
    private final static int UPGRADE_BLOCK = 4;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public Push() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
        cardsToPreview = new Wound();
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        blck();
        shuffleIn(new Wound());
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}