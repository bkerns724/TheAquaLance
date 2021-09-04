package theAquaLance.cards;

import static theAquaLance.AquaLanceMod.makeID;

public class IronWave extends AbstractWaveCard {
    public final static String ID = makeID("IronWave");
    private final static int DAMAGE = 7;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int BLOCK = 4;
    private final static int UPGRADE_BLOCK = 2;
    private final static int BLOCK_BONUS = 4;
    private final static int BONUS_UPGRADE = 2;

    public IronWave() {
        super(ID, 1, CardRarity.UNCOMMON);
        damageCore = baseDamage = DAMAGE;
        blockCore = baseBlock = BLOCK;
        blockBonus = BLOCK_BONUS;
        baseSecondMagic = secondMagic = blockBonus;
        setDescription();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeBlock(UPGRADE_BLOCK);
        damageCore += UPGRADE_DAMAGE;
        blockCore += UPGRADE_BLOCK;
        blockBonus += BONUS_UPGRADE;
        baseSecondMagic = secondMagic = blockBonus;
        upgradedSecondMagic = true;
        calculateBonuses();
        setDescription();
    }
}