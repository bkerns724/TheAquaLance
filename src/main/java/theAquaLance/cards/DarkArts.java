package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theAquaLance.AquaLanceMod;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class DarkArts extends AbstractEasyCard {
    public final static String ID = makeID("DarkArts");
    private final static int MAGIC = 8;
    private final static int UPGRADE_MAGIC = 3;
    private final static int SECOND_DAMAGE = 8;
    private final static int UPGRADE_SECOND = 3;
    private final static int COST = -1;

    public DarkArts() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseSecondDamage = SECOND_DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (adp().hasRelic("Chemical X"))
            adp().getRelic("Chemical X").flash();

        calculateCardDamage(m);
        dmgTwo(m, AquaLanceMod.Enums.BLOOD);

        if (!freeToPlayOnce) {
            adp().energy.use(EnergyPanel.totalCount);
        }
    }

    @Override
    public void applyPowers() {
        int energyCount = getEnergyAvailable();
        baseSecondDamage = baseMagicNumber * energyCount;
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int energyCount = getEnergyAvailable();
        baseSecondDamage = baseMagicNumber * energyCount;
        super.calculateCardDamage(mo);
    }

    private int getEnergyAvailable() {
        int effect = EnergyPanel.totalCount;

        if (adp().hasRelic("Chemical X"))
            effect += 2;
        return effect;
    }

    public void upp() {
        upgradeSecondDamage(UPGRADE_SECOND);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}