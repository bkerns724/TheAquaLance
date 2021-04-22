package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.AbstractCardPatch.AbstractCardField;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class DamBreak extends AbstractEasyCard {
    public final static String ID = makeID("DamBreak");
    private final static int MAGIC = 1;

    public DamBreak() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        isInnate = true;
        selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DiscardAction(p, p, magicNumber, false));
    }

    public void upp() {
        AbstractCardField.replenish.set(this, true);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}