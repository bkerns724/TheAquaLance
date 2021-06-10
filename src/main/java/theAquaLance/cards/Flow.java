package theAquaLance.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Flow extends AbstractEasyCard {
    public final static String ID = makeID("Flow");
    private final static int BLOCK = 4;
    private final static int UPGRADE_BLOCK = 1;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 2;

    public Flow() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseBlock = baseBlock;
        baseBlock = realBaseBlock + getAttackCount()*magicNumber;
        super.calculateCardDamage(mo);
        baseBlock = realBaseBlock;
        isBlockModified = block != baseBlock;
    }

    @Override
    public void applyPowers() {
        int realBaseBlock = baseBlock;
        baseBlock = realBaseBlock + getAttackCount()*magicNumber;
        super.applyPowers();
        baseBlock = realBaseBlock;
        isBlockModified = block != baseBlock;
    }

    private int getAttackCount() {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn)
            if (c.type == CardType.ATTACK)
                count++;
        return count;
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}