package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.ExplosiveSigilPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class JaggedSigil extends AbstractArcanistCard {
    public final static String ID = makeID("JaggedSigil");
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;

    public JaggedSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    @Override
    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ExplosiveSigilPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}