package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.ExileStaticDischargePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class StaticDischarge extends AbstractExileCard {
    public final static String ID = makeID(StaticDischarge.class.getSimpleName());
    private final static int MAGIC = 11;
    private final static int UPGRADE_MAGIC = 4;
    private final static int COST = -2;

    public StaticDischarge() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ExileStaticDischargePower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}