package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArcanist.ArcanistMod.makeID;

public class HardSigil extends AbstractArcanistCard {
    public final static String ID = makeID(HardSigil.class.getSimpleName());
    private final static int BLOCK = 10;
    private final static int UPGRADE_BLOCK = 3;

    public HardSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        sigil = true;
    }

    @Override
    public void onUse(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}