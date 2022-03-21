package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.DecayingPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class CrumblingSigil extends AbstractArcanistCard {
    public final static String ID = makeID(CrumblingSigil.class.getSimpleName());
    private final static int MAGIC = 7;
    private final static int UPGRADE_MAGIC = 2;

    public CrumblingSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        magicOneIsDebuff = true;
        sigil = true;
    }

    @Override
    public void onUse(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(monster -> applyToEnemy(monster, new DecayingPower(monster, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}