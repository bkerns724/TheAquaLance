package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.actions.ShadowCloakBlockAction;
import theArcanist.powers.ShadowcloakPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Vanish extends AbstractArcanistCard {
    public final static String ID = makeID("Vanish");
    private final static int BLOCK = 0;
    private final static int MAGIC = 2;
    private final static int COST = 2;
    private final static int SECOND_MAGIC = 3;
    private final static int UPGRADE_SECOND = 1;

    public Vanish() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
        secondMagic = baseSecondMagic = SECOND_MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ShadowcloakPower(p, magicNumber));
        atb(new ShadowCloakBlockAction(this));
    }

    @Override
    public void applyPowers() {
        if (adp().hasPower(ShadowcloakPower.POWER_ID))
            baseBlock = secondMagic*adp().getPower(ShadowcloakPower.POWER_ID).amount;
        super.applyPowers();
        baseBlock = BLOCK;
    }

    public void upp() {
        upMagic2(UPGRADE_SECOND);
    }
}