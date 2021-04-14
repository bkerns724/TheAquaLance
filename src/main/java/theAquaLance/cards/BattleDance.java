package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.cards.AbstractEasyCard;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class BattleDance extends AbstractEasyCard {
    public final static String ID = makeID("BattleDance");
    private final static int BLOCK = 6;
    private final static int UPGRADE_BLOCK = 2;
    private final static int MAGIC = 6;
    private final static int UPG_MAGIC = 2;

    public BattleDance() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void applyPowers() {
        int realBaseBlock = baseBlock;
        int enemyCount = getEnemies().size();
        baseBlock = baseBlock*enemyCount;
        super.applyPowers();
        baseBlock = realBaseBlock;
        isBlockModified = block != baseBlock;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseBlock = baseBlock;
        int enemyCount = getEnemies().size();
        baseBlock = baseBlock*enemyCount;
        super.calculateCardDamage(mo);
        baseBlock = realBaseBlock;
        isBlockModified = block != baseBlock;
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upgradeMagicNumber(UPG_MAGIC);
    }
}