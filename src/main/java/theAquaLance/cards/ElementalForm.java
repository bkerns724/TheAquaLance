package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.AbstractCardPatch;
import theAquaLance.powers.ElementalFormPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class ElementalForm extends AbstractEasyCard {
    public final static String ID = makeID("ElementalForm");
    private final static int MAGIC = 1;

    public ElementalForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ElementalFormPower(p, magicNumber));
    }

    public void upp() {
        AbstractCardPatch.AbstractCardField.replenish.set(this, true);
        uDesc();
    }
}