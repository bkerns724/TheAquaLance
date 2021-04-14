package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.AbstractCardPatch.AbstractCardField;
import theAquaLance.powers.VitalityPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class IceSigil extends AbstractEasyCard {
    public final static String ID = makeID("IceSigil");
    private final static int BLOCK = 7;
    private final static int UPGRADE_BLOCK = 2;

    public IceSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseBlock = BLOCK;
        AbstractCardField.sigil.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void triggerOnManualDiscard() {
        blck();
        atb(new RemoveSpecificPowerAction(adp(), adp(), VitalityPower.POWER_ID));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}