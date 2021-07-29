package theAquaLance.cards;

import static theAquaLance.AquaLanceMod.makeID;

public class ChillingWave extends AbstractWaveCard {
    public final static String ID = makeID("ChillingWave");
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int BONUS = 3;
    private final static int BONUS_UPGRADE = 1;

    public ChillingWave() {
        super(ID, 1, CardRarity.UNCOMMON);
        damageCore = baseDamage = DAMAGE;
        magicCore = baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = magicBonus = BONUS;
        setDescription();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        damageCore += UPGRADE_DAMAGE;
        upgradeMagicNumber(UPGRADE_MAGIC);
        magicCore += UPGRADE_MAGIC;
        baseSecondMagic = secondMagic = magicBonus += BONUS_UPGRADE;
        upgradedSecondMagic = true;
        calculateBonuses();
        setDescription();
    }
}