package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.ExileStaticDischargePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class StaticDischarge extends AbstractExileCard {
    public final static String ID = makeID(StaticDischarge.class.getSimpleName());
    private final static int BLOCK = 12;
    private final static int UPGRADE_BLOCK = 4;
    private final static int MAGIC = 8;
    private final static int UPGRADE_MAGIC = 3;
    private final static int COST = 2;

    public StaticDischarge() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseBlock = BLOCK;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new ExileStaticDischargePower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeBlock(UPGRADE_BLOCK);
    }
}