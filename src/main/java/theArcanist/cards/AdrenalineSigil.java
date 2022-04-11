package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.applyToSelf;

public class AdrenalineSigil extends AbstractArcanistCard {
    public final static String ID = makeID(AdrenalineSigil.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;

    public AdrenalineSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
        initializeDescription();
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ArtifactPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}