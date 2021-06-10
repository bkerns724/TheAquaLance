package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.AbstractCardPatch;

import static theAquaLance.AquaLanceMod.makeID;

import static theAquaLance.util.Wiz.*;

public class Reserves extends AbstractEasyCard {
    public final static String ID = makeID("Reserves");
    private final static int MAGIC = 1;

    public Reserves() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
    }

    public void upp() {
        AbstractCardPatch.AbstractCardField.replenish.set(this, true);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}