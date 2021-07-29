package theAquaLance.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.AbstractCardPatch;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Spray extends AbstractEasyCard {
    public final static String ID = makeID("Spray");

    public Spray() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DiscardAction(adp(), adp(), BaseMod.MAX_HAND_SIZE, true));
    }

    public void upp() {
        AbstractCardPatch.AbstractCardField.replenish.set(this, true);
        uDesc();
    }
}