package theAquaLance.cards;

import static theAquaLance.AquaLanceMod.makeID;

public class RideTheWave extends AbstractWaveCard {
    public final static String ID = makeID("RideTheWave");
    private final static int DAMAGE = 15;
    private final static int UPGRADE_DAMAGE = 5;
    private final static int DAMAGE_BONUS = 8;
    private final static int BONUS_UPGRADE = 2;

    public RideTheWave() {
        super(ID, 2, CardRarity.RARE);
        target = CardTarget.ALL_ENEMY;
        damageCore = baseDamage = DAMAGE;
        damageBonus = DAMAGE_BONUS;
        areaCore = areaAttack = true;
        secondMagic = baseSecondMagic = damageBonus;
        isMultiDamage = true;
        setDescription();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        damageCore += UPGRADE_DAMAGE;
        damageBonus += BONUS_UPGRADE;
        secondMagic = baseSecondMagic = damageBonus;
        upgradedSecondMagic = true;
        calculateBonuses();
        setDescription();
    }
}