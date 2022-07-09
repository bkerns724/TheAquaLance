package theArcanist.cards;

import static theArcanist.ArcanistMod.makeID;

public class VengefulChanneling extends AbstractResonantCard {
    public final static String ID = makeID(VengefulChanneling.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public VengefulChanneling() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        resonance.revenge = baseMagicNumber;
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
        resonance.revenge += UPGRADE_MAGIC;
    }
}