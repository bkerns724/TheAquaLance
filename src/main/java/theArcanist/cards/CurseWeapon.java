package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.JinxPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class CurseWeapon extends AbstractArcanistCard {
    public final static String ID = makeID("CurseWeapon");
    private final static int BLOCK = 10;
    private final static int UPGRADE_BLOCK = 3;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 2;

    public CurseWeapon() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToEnemy(m, new JinxPower(m, magicNumber));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}