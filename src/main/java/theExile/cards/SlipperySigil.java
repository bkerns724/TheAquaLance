package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;

public class SlipperySigil extends AbstractExileCard {
    public final static String ID = makeID(SlipperySigil.class.getSimpleName());
    private final static int BLOCK = 10;
    private final static int UPGRADE_BLOCK = 4;

    public SlipperySigil() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
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