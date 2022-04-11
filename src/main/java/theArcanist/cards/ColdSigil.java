package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.FrostbitePower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class ColdSigil extends AbstractArcanistCard {
    public final static String ID = makeID(ColdSigil.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public ColdSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        magicOneIsDebuff = true;
        sigil = true;
        initializeDescription();
    }

    @Override
    public void onUse(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(monster -> {
            int deb = getDebuffCount(monster);
            if (deb > 0)
                applyToEnemy(monster, new FrostbitePower(monster, deb*magicNumber));
        });
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}