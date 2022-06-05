package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.discard;

public class Blink extends AbstractArcanistCard {
    public final static String ID = makeID(Blink.class.getSimpleName());
    private final static int BLOCK = 11;
    private final static int UPGRADE_BLOCK = 4;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public Blink() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        blck();
        discard(magicNumber);
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}