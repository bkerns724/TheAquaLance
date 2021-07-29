package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.actions.PopAllAction;
import theAquaLance.powers.EmbedPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Detonate extends AbstractEasyCard {
    public final static String ID = makeID("Detonate");
    private final static int MAGIC = 10;
    private final static int UPGRADE_MAGIC = 3;
    private final static int SECOND_DAMAGE = 10;
    private final static int UPGRADE_SECOND = 3;
    private final static int COST = 2;

    public Detonate() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseSecondDamage = SECOND_DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmgTwo(AquaLanceMod.Enums.WATER);
        EmbedPower pow = (EmbedPower) m.getPower(EmbedPower.POWER_ID);
        if (pow != null)
            atb(new PopAllAction(m));
    }

    @Override
    public void applyPowers() {
        baseSecondDamage = 0;
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int count = getShardCount(mo);
        baseSecondDamage = baseMagicNumber*count;
        super.calculateCardDamage(mo);
    }

    public void upp() {
        upgradeSecondDamage(UPGRADE_SECOND);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}