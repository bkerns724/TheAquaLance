package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.SoulFlameBarrierPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class FlamingSigil extends AbstractExileCard {
    public final static String ID = makeID(FlamingSigil.class.getSimpleName());
    private final static int MAGIC = 8;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = -2;

    public FlamingSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SoulFlameBarrierPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}