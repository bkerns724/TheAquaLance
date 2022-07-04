package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.cards.cardUtil.Resonance;
import theArcanist.powers.DecayingPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.applyToEnemy;

public class CorruptedChanneling extends AbstractResonantCard {
    public final static String ID = makeID(CorruptedChanneling.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public CorruptedChanneling() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        resonance = new Resonance(baseDamage);
        resonance.decay = baseMagicNumber;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
        applyToEnemy(m, new DecayingPower(m, magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
        resonance.decay += UPGRADE_MAGIC;
    }
}