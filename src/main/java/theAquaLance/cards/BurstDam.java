package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.AquaDrawCardAction;
import theAquaLance.actions.AquaGainEnergyAction;
import theAquaLance.actions.IncreaseCostAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class BurstDam extends AbstractEasyCard {
    public final static String ID = makeID("BurstDam");
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 1;
    private final static int UPG_SECOND_MAGIC = 1;
    private final static int DISCARD_AMOUNT = 1;
    private final static int COST_INCREASE = 1;

    public BurstDam() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AquaDrawCardAction(magicNumber));
        atb(new AquaGainEnergyAction(secondMagic));
        atb(new DiscardAction(p, p, DISCARD_AMOUNT, false));
        atb(new IncreaseCostAction(this.uuid, COST_INCREASE));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeSecondMagic(UPG_SECOND_MAGIC);
    }
}