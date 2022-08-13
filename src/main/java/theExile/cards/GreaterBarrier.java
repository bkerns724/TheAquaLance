package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;

public class GreaterBarrier extends AbstractExileCard {
    public final static String ID = makeID(GreaterBarrier.class.getSimpleName());
    private final static int BLOCK = 20;
    private final static int UPGRADE_BLOCK = 5;
    private final static int COST = 2;

    public GreaterBarrier() {
        super(ID, COST, CardType.SKILL, ExileMod.Enums.UNIQUE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseBlock = BLOCK;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}