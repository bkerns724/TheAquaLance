package theExile.cards;

import static theExile.ExileMod.makeID;

public class ChanneledCurse extends AbstractResonantCard {
    public final static String ID = makeID(ChanneledCurse.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public ChanneledCurse() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void setResonance() {
        resonance.jinx = baseMagicNumber;
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        magicOneIsDebuff = true;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        resonance.jinx += UPGRADE_MAGIC;
    }
}