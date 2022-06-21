package theArcanist.cards;

import theArcanist.cards.cardUtil.Resonance;

import static theArcanist.ArcanistMod.makeID;

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
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        magicOneIsDebuff = true;
        resonance = new Resonance(baseDamage);
        resonance.jinx = baseMagicNumber;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}