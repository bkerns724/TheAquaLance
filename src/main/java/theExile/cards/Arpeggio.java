package theExile.cards;

import static theExile.ExileMod.makeID;

public class Arpeggio extends AbstractResonantCard {
    public final static String ID = makeID(Arpeggio.class.getSimpleName());
    private final static int DAMAGE = 4;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public Arpeggio() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    protected void setResonance() {
        resonance.damage = baseDamage*baseMagicNumber;
        resonance.multi = magicNumber;
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
        resonance.damage += baseDamage*UPGRADE_MAGIC;
        resonance.multi += UPGRADE_MAGIC;
    }
}