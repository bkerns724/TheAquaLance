package theArcanist.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class SonicReverberation extends AbstractArcanistCard {
    public final static String ID = makeID("SonicReverberation");
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;
    private final static int DISCARD_AMOUNT = 1;

    public SonicReverberation() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new VulnerablePower(m, getJinxAmount(m), false));
        atb(new DrawCardAction(magicNumber));
        discard(DISCARD_AMOUNT);
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}