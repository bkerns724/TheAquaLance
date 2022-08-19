package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.forAllMonstersLiving;

public class Confusion extends AbstractExileCard {
    public final static String ID = makeID(Confusion.class.getSimpleName());
    private final static int BLOCK = 7;
    private final static int UPGRADE_BLOCK = 2;
    private final static int COST = 1;

    public Confusion() {
        super(ID, COST, CardType.SKILL, ExileMod.Enums.UNIQUE, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseBlock = BLOCK;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(mon -> blck());
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}