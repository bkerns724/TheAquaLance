package theArcanist.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import theArcanist.cards.cardUtil.Resonance;

import static theArcanist.ArcanistMod.makeID;

public class DefensiveChanneling extends AbstractResonantCard {
    public final static String ID = makeID(DefensiveChanneling.class.getSimpleName());
    public final static String LOC_NAME = CardCrawlGame.languagePack.getCardStrings(ID).NAME;
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int BLOCK = 3;
    private final static int UPGRADE_BLOCK = 1;
    private final static int COST = 1;

    public DefensiveChanneling() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        resonance = new Resonance(baseDamage);
        resonance.block = baseBlock;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeBlock(UPGRADE_BLOCK);
    }
}