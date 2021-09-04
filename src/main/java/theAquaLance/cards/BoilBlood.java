package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.AquaLanceMod;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class BoilBlood extends AbstractEasyCard {
    public final static String ID = makeID("BoilBlood");
    private final static int SECOND_DAMAGE = 10;
    private final static int MAGIC = 2;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public BoilBlood() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseSecondDamage = secondDamage = SECOND_DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        calculateCardDamage(m);
        dmgTwo(m, AquaLanceMod.Enums.BLOOD);
    }

    @Override
    public void applyPowers() {
        AbstractPower intelligence = AbstractDungeon.player.getPower("intelligence");
        if (intelligence != null)
            intelligence.amount *= magicNumber;

        super.applyPowers();
        if (intelligence != null)
            intelligence.amount /= magicNumber;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower intelligence = AbstractDungeon.player.getPower("intelligence");
        if (intelligence != null)
            intelligence.amount *= magicNumber;

        super.calculateCardDamage(mo);
        if (intelligence != null)
            intelligence.amount /= magicNumber;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeSecondDamage(UPGRADE_DAMAGE);
    }
}