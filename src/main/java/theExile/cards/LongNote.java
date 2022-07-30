package theExile.cards;

import static theExile.ExileMod.makeID;

public class LongNote extends AbstractResonantCard {
    public final static String ID = makeID(LongNote.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public LongNote() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    protected void setResonance() {
        resonance.damage = baseDamage;
        resonance.ringing = baseMagicNumber;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        resonance.damage += UPGRADE_DAMAGE;
        upgradeMagicNumber(UPGRADE_MAGIC);
        resonance.ringing += UPGRADE_MAGIC;
    }
}