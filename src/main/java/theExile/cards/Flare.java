package theExile.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class Flare extends AbstractExileCard {
    public final static String ID = makeID(Flare.class.getSimpleName());
    private final static int BLOCK = 4;
    private final static int UPGRADE_BLOCK = 2;
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 0;

    public Flare() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        blck();
    }

    @Override
    public void applyPowers() {
        int temp = baseBlock;
        if (checkCondition())
            baseBlock = baseBlock + magicNumber;
        super.applyPowers();
        baseBlock = temp;
        isBlockModified = baseBlock != block;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int temp = baseBlock;
        if (checkCondition())
            baseBlock = baseBlock + magicNumber;
        super.calculateCardDamage(mo);
        baseBlock = temp;
        isBlockModified = baseBlock != block;
    }

    public void triggerOnGlowCheck() {
        if (checkCondition())
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        else
            glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

    private boolean checkCondition() {
        if (adp().hand.isEmpty() || (adp().hand.size() == 1 && adp().hand.contains(this)))
            return true;
        return false;
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upMagic(UPGRADE_MAGIC);
    }
}