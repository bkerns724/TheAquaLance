package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.getDebuffCount;
import static theExile.util.Wiz.getEnemies;

public class ShiningSigil extends AbstractExileCard {
    public final static String ID = makeID(ShiningSigil.class.getSimpleName());
    private final static int BLOCK = 4;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = -2;

    public ShiningSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    public void nonTargetUse() {
        blck();
    }

    @Override
    public void applyPowers() {
        int temp = baseBlock;
        int count = 0;
        for (AbstractMonster mon : getEnemies())
            count += getDebuffCount(mon);
        baseBlock += count*magicNumber;
        super.applyPowers();
        baseBlock = temp;
        isBlockModified = block != baseBlock;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int temp = baseBlock;
        int count = 0;
        for (AbstractMonster mon : getEnemies())
            count += getDebuffCount(mon);
        baseBlock += count*magicNumber;
        super.calculateCardDamage(mo);
        baseBlock = temp;
        isBlockModified = block != baseBlock;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}