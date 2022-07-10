package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.BoostedSigilPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class RampingSigil extends AbstractExileCard {
    public final static String ID = makeID(RampingSigil.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = -2;

    public RampingSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BoostedSigilPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}