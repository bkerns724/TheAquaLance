package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.DiscardNextTurnPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;
import static theExile.util.Wiz.discard;

public class Blink extends AbstractExileCard {
    public final static String ID = makeID(Blink.class.getSimpleName());
    private final static int BLOCK = 12;
    private final static int UPGRADE_BLOCK = 3;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public Blink() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseBlock = BLOCK;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        blck();
        discard(1);
        applyToSelf(new DiscardNextTurnPower(1));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}