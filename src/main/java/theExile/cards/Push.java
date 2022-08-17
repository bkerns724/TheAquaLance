package theExile.cards;

import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.DrawNextTurnPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Push extends AbstractExileCard {
    public final static String ID = makeID(Push.class.getSimpleName());
    private final static int BLOCK = 12;
    private final static int UPGRADE_BLOCK = 4;
    private final static int MAGIC = 2;
    private final static int COST = 2;

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
        applyToSelf(new DrawNextTurnPower(magicNumber));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}