package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.ShadowcloakPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class Invisibility extends AbstractExileCard {
    public final static String ID = makeID(Invisibility.class.getSimpleName());
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;
    private final static int SECOND_MAGIC = 1;
    private final static int COST = 2;

    public Invisibility() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ShadowcloakPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}