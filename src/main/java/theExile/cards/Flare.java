package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class Flare extends AbstractExileCard {
    public final static String ID = makeID(Flare.class.getSimpleName());
    private final static int BLOCK = 8;
    private final static int UPGRADE_BLOCK = 3;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 2;

    public Flare() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        if (block > 0)
            blck();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int temp = baseBlock;
        baseBlock += magicNumber*debuffAllCount();
        super.calculateCardDamage(mo);
        baseBlock = temp;
        if (block != baseBlock)
            isBlockModified = true;
    }

    @Override
    public void applyPowers() {
        int temp = baseBlock;
        baseBlock += magicNumber*debuffAllCount();
        super.applyPowers();
        baseBlock = temp;
        if (block != baseBlock)
            isBlockModified = true;
    }

    public int debuffAllCount() {
        int count = 0;
        for (AbstractMonster m : getEnemies())
            count += getDebuffCount(m);
        return count;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeBlock(UPGRADE_BLOCK);
    }
}