package theAquaLance.cards;

import static theAquaLance.AquaLanceMod.makeID;

public class WaveMotion extends AbstractWaveCard {
    public final static String ID = makeID("WaveMotion");
    private final static int DAMAGE = 7;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int DAMAGE_BONUS = 4;
    private final static int BONUS_UPGRADE = 1;

    public WaveMotion() {
        super(ID, 0, CardRarity.COMMON);
        damageCore = baseDamage = DAMAGE;
        damageBonus = DAMAGE_BONUS;
        baseSecondMagic = secondMagic = damageBonus;
        setDescription();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        damageCore += UPGRADE_DAMAGE;
        damageBonus += BONUS_UPGRADE;
        baseSecondMagic = secondMagic = damageBonus;
        upgradedSecondMagic = true;
        calculateBonuses();
        setDescription();
    }
}