package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.powers.JinxThornsPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class ScourgeBubble extends AbstractArcanistCard {
    public final static String ID = makeID("ScourgeBubble");
    private final static int BLOCK = 8;
    private final static int UPGRADE_BLOCK = 4;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public ScourgeBubble() {
        super(ID, COST, CardType.SKILL, ArcanistMod.Enums.UNIQUE, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new JinxThornsPower(p, magicNumber));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}