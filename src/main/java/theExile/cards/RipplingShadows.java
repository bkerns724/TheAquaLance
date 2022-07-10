package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.ShadowcloakPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class RipplingShadows extends AbstractExileCard {
    public final static String ID = makeID(RipplingShadows.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;
    private final static int SECOND_MAGIC = 1;

    public RipplingShadows() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ShadowcloakPower(p, magicNumber));
        cardDraw(secondMagic);
        discard(secondMagic);
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}