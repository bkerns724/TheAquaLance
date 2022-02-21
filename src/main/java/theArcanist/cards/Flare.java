package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Flare extends AbstractArcanistCard {
    public final static String ID = makeID("Flare");
    private final static int BLOCK = 0;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public Flare() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        baseBlock = magicNumber*getDebuffCount(mo);
        applyPowersToBlock();
        super.calculateCardDamage(mo);
        baseBlock = BLOCK;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}