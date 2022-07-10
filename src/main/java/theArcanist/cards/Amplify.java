package theArcanist.cards;

import static theArcanist.ArcanistMod.makeID;

public class Amplify extends AbstractResonantCard {
    public final static String ID = makeID(Amplify.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public Amplify() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void setResonance() {
        resonance.amplify = baseMagicNumber;
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
        resonance.amplify += UPGRADE_MAGIC;
    }
}