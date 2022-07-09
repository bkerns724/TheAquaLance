package theArcanist.cards;

import static theArcanist.ArcanistMod.makeID;

public class DefensiveChanneling extends AbstractResonantCard {
    public final static String ID = makeID(DefensiveChanneling.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int BLOCK = 3;
    private final static int UPGRADE_BLOCK = 1;
    private final static int COST = 1;

    public DefensiveChanneling() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        resonance.block = baseBlock;
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeBlock(UPGRADE_BLOCK);
        resonance.amount += UPGRADE_DAMAGE;
        resonance.block += UPGRADE_BLOCK;
    }
}