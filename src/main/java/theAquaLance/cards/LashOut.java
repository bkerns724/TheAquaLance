package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.AquaLanceMod;
import theAquaLance.powers.DiscardNextTurnPower;
import theAquaLance.powers.IntelligencePower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class LashOut extends AbstractEasyCard {
    public final static String ID = makeID("LashOut");
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_DAMAGE = 12;
    private final static int SECOND_MAGIC = 2;
    private final static int COST = 0;

    public LashOut() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseSecondDamage = SECOND_DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        calculateCardDamage(m);
        dmgTwo(m, AquaLanceMod.Enums.BLOOD);
        applyToSelf(new DiscardNextTurnPower(adp(), secondMagic));
    }

    @Override
    public void applyPowers() {
        AbstractPower intelligence = AbstractDungeon.player.getPower(IntelligencePower.POWER_ID);
        if (intelligence != null)
            intelligence.amount *= magicNumber;

        super.applyPowers();
        if (intelligence != null)
            intelligence.amount /= magicNumber;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower intelligence = AbstractDungeon.player.getPower(IntelligencePower.POWER_ID);
        if (intelligence != null)
            intelligence.amount *= magicNumber;

        super.calculateCardDamage(mo);
        if (intelligence != null)
            intelligence.amount /= magicNumber;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}