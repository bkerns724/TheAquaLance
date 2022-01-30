package theArcanist.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Blink extends AbstractArcanistCard {
    public final static String ID = makeID("Blink");
    private final static int BLOCK = 10;
    private final static int UPGRADE_BLOCK = 3;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    // skill, uncommon, self
    public Blink() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new DiscardAction(p, p, magicNumber, false));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}