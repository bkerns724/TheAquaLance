package theAquaLance.cards;

import static theAquaLance.AquaLanceMod.makeID;

public class MagicWave extends AbstractWaveCard {
    public final static String ID = makeID("MagicWave");
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 6;
    private final static int UPGRADE_MAGIC = 2;
    private final static int MAGIC_BONUS = 6;
    private final static int BONUS_UPGRADE = 2;

    public MagicWave() {
        super(ID, 1, CardRarity.UNCOMMON);
        damageCore = baseDamage = DAMAGE;
        magicCore = baseMagicNumber = magicNumber = MAGIC;
        magicBonus = MAGIC_BONUS;
        baseSecondMagic = secondMagic = magicBonus;
        setDescription();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
        damageCore += UPGRADE_DAMAGE;
        magicCore += UPGRADE_MAGIC;
        magicBonus += BONUS_UPGRADE;
        baseSecondMagic = secondMagic = magicBonus;
        upgradedSecondMagic = true;
        calculateBonuses();
        setDescription();
    }
}