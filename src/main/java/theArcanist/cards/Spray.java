package theArcanist.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.InvisibleSprayPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Spray extends AbstractEasyCard {
    public final static String ID = makeID("Spray");
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public Spray() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = MAGIC;
        selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new InvisibleSprayPower(adp(), magicNumber));
        atb(new DiscardAction(adp(), adp(), BaseMod.MAX_HAND_SIZE, true));
        atb(new RemoveSpecificPowerAction(adp(), adp(), InvisibleSprayPower.POWER_ID));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}