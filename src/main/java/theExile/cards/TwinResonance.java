package theExile.cards;

import static theExile.ExileMod.makeID;

public class TwinResonance extends AbstractResonantCard {
    public final static String ID = makeID(TwinResonance.class.getSimpleName());
    private final static int DAMAGE = 4;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public TwinResonance() {
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
        name = cardStrings.EXTENDED_DESCRIPTION[0];
    }
}